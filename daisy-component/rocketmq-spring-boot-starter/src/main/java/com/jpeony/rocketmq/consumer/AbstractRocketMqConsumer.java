package com.jpeony.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.jpeony.rocketmq.lifecycle.AbstractLifeCycle;
import com.jpeony.rocketmq.property.RocketMqBaseProperty;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yihonglei
 */
public abstract class AbstractRocketMqConsumer extends AbstractLifeCycle {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected DefaultMQPushConsumer consumer = null;
    private final static AtomicInteger NUMBER = new AtomicInteger(0);
    /**
     * mq 相关配置
     */
    private RocketMqBaseProperty mqProperty;

    /**
     * 是否开启
     */
    private volatile boolean start = false;

    @Override
    public void start() {
        synchronized (this.getClass()) {
            this.mqProperty = getMqProperty();
            init();
        }
    }

    private void init() {
        if (start) {
            logger.warn("the consumer [{}] is started.", this);
            return;
        }
        String namesrvAddr = mqProperty.getNamesrvAddr();
        if (isEmpty(namesrvAddr)) {
            throw new IllegalStateException("RocketMqConsumer.namesrvAddr is required");
        }
        String groupName = mqProperty.getGroupName();
        if (isEmpty(groupName)) {
            throw new IllegalStateException("RocketMqConsumer.groupName is required");
        }
        String topic = mqProperty.getTopic();
        if (isEmpty(topic)) {
            throw new IllegalStateException("RocketMqConsumer.topic is required");
        }
        String instanceName = mqProperty.getInstanceName();
        if (isEmpty(instanceName)) {
            instanceName = "consumer-" + NUMBER.incrementAndGet() + "-" + System.currentTimeMillis();
        }
        int batchSize = mqProperty.getBatchMaxSize();
        if (batchSize <= 0) {
            batchSize = 16;
        }
        String tag = mqProperty.getTag();
        if (isEmpty(tag)) {
            tag = "*";
        }

        String finalTag = tag;
        consumer = new DefaultMQPushConsumer(groupName);
        consumer.setInstanceName(instanceName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeMessageBatchMaxSize(batchSize);
        try {
            consumer.subscribe(topic, finalTag);
            // 注册监听
            if (this.listener() == MessageListenerConcurrently.class) {
                consumer.registerMessageListener((MessageListenerConcurrently) (msg, context) -> {
                    ConsumeConcurrentlyStatus status = null;
                    try {
                        status = handleMessage(msg, context);
                    } catch (Exception e) {
                        logger.error("消费失败topic:[{}] tag:[{}] msg:[{}]", topic, finalTag, JSON.toJSONString(msg), e);
                        throw e;
                    }
                    return status;
                });
            } else if (this.listener() == MessageListenerOrderly.class) {
                consumer.registerMessageListener((MessageListenerOrderly) (msg, context) -> {
                    ConsumeOrderlyStatus status = null;
                    try {
                        status = handleOrderlyMessage(msg, context);
                    } catch (Exception e) {
                        logger.error("消费失败topic:[{}] tag:[{}] msg:[{}]", topic, finalTag, JSON.toJSONString(msg), e);
                        throw e;
                    }
                    return status;
                });
            } else {
                throw new UnsupportedOperationException();
            }
            consumer.start();
            start = true;
            logger.info("rocketMq consumer [namesrvAddr={} groupName={} topic={} tag={} instanceName={}] 启动完成",
                    namesrvAddr, groupName, topic, mqProperty.getTag(), instanceName);
        } catch (MQClientException e) {
            logger.warn("rocketMq consumer [namesrvAddr={} groupName={} topic={} tag={} instanceName={}] 启动失败",
                    namesrvAddr, groupName, topic, mqProperty.getTag(), instanceName);
            throw new IllegalStateException("mq consumer init fail", e);
        }

    }

    /**
     * 获取扩展信息
     */
    protected final Map<String, Object> getExt() {
        return null;
    }

    /**
     * 顺序消费消息
     */
    protected ConsumeOrderlyStatus handleOrderlyMessage(final List<MessageExt> msgs, final ConsumeOrderlyContext context) {
        throw new UnsupportedOperationException();
    }

    /**
     * 非顺序消费消息
     */
    protected ConsumeConcurrentlyStatus handleMessage(final List<MessageExt> msgs, final ConsumeConcurrentlyContext context) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取配信息
     */
    public abstract RocketMqBaseProperty getMqProperty();

    /**
     * 消费顺序 默认无序
     */
    public Class<? extends MessageListener> listener() {
        return MessageListenerConcurrently.class;
    }

    @Override
    public boolean isStart() {
        return start;
    }

    @Override
    public void shutdown() {
        if (consumer != null) {
            consumer.shutdown();
            start = false;
            logger.info("the consumer [{}] shutdown OK", this);
        }
    }
}
