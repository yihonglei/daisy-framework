package com.lanhuigu.rocketmq.starter.consumer;

import com.lanhuigu.rocketmq.starter.annotation.RocketMqConsumer;
import com.lanhuigu.rocketmq.starter.lifecycle.AbstractLifeCycle;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author yihonglei
 */
public abstract class AbstractRocketMqConsumer extends AbstractLifeCycle {

    protected DefaultMQPushConsumer consumer = null;
    private static volatile boolean isStart = false;
    @Autowired
    private Environment env;

    @Override
    public void start() {
        listen();
    }

    @Override
    public boolean isStart() {
        return isStart;
    }

    @Override
    public void shutdown() {
        if (consumer != null) {
            logger.warn("shutdown class:[{}]", this);
            consumer.shutdown();
        }
    }

    public void listen() {
        RocketMqConsumer config = this.getClass().getAnnotation(RocketMqConsumer.class);
        if (config == null) {
            logger.warn("未找到消费参数 RocketMqConsumer is null");
            return;
        }
        String groupName = env.getProperty(config.groupName());
        Assert.isTrue(StringUtils.isNotBlank(groupName), "groupName must not be null");
        String topic = env.getProperty(config.topic());
        if (StringUtils.isBlank(topic)) {
            topic = topic();
        }
        Assert.isTrue(StringUtils.isNotBlank(groupName), "topic must not be null");

        // MQ地址
        String namesrvAddr = env.getProperty(config.namesrvAddr());
        Assert.isTrue(StringUtils.isNotBlank(namesrvAddr), "namesrvAddr must not be null");
        String instanceName = env.getProperty(config.instanceName());
        if (StringUtils.isBlank(instanceName)) {
            instanceName = "consumer_" + System.currentTimeMillis();
        }
        int batchSize = config.batchMaxSize();
        if (batchSize <= 0) {
            batchSize = 16;
        }
        String tag = env.getProperty(config.tag());

        try {
            consumer = new DefaultMQPushConsumer(groupName);
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.setInstanceName(instanceName);
            consumer.setConsumeMessageBatchMaxSize(batchSize);
            if (StringUtils.isNotBlank(tag)) {
                consumer.subscribe(topic, tag);
            } else {
                consumer.subscribe(topic, "*");
            }
            // 注册监听
            if (config.listener() == MessageListenerConcurrently.class) {
                consumer.registerMessageListener((MessageListenerConcurrently) this::handleMessage);
            } else if (config.listener() == MessageListenerOrderly.class) {
                consumer.registerMessageListener((MessageListenerOrderly) this::handleMessage);
            }
            consumer.start();
            logger.info("rocketMq producer [namesrvAddr={} groupName={} topic={} tag={} instanceName={} 启动完成",
                    namesrvAddr, groupName, topic, tag, instanceName);
            isStart = true;
        } catch (MQClientException e) {
            logger.info("rocketMq producer [namesrvAddr={} groupName={} topic={} tag={} instanceName={} 启动失败",
                    namesrvAddr, groupName, topic, tag, instanceName, e);
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 顺序消费消息
     */
    public ConsumeOrderlyStatus handleMessage(final List<MessageExt> msgs, final ConsumeOrderlyContext context) {
        return ConsumeOrderlyStatus.SUCCESS;
    }

    /**
     * 非顺序消费消息
     */
    public ConsumeConcurrentlyStatus handleMessage(final List<MessageExt> msgs, final ConsumeConcurrentlyContext context) {
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * topic名称
     */
    protected String topic() {
        return null;
    }
}
