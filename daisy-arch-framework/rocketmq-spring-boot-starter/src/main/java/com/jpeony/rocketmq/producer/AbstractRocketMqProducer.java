package com.jpeony.rocketmq.producer;

import com.jpeony.rocketmq.lifecycle.AbstractLifeCycle;
import com.jpeony.rocketmq.property.RocketMqBaseProperty;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;

/**
 * @author yihonglei
 */
public abstract class AbstractRocketMqProducer extends AbstractLifeCycle implements Producer {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected DefaultMQProducer producer = null;
    /**
     * mq 相关配置
     */
    private RocketMqBaseProperty mqProperty;
    private final static AtomicInteger NUMBER = new AtomicInteger(0);

    /**
     * 是否开启
     */
    private volatile boolean start = false;

    @Override
    public void start() {
        synchronized (AbstractRocketMqProducer.class) {
            this.mqProperty = getMqProperty();
            init();
        }
    }

    /**
     * 初始化
     */
    private void init() {
        if (start) {
            logger.warn("the producer [{}] is started.", this);
            return;
        }
        String namesrvAddr = mqProperty.getNamesrvAddr();
        if (isEmpty(namesrvAddr)) {
            throw new IllegalStateException("RocketMqProducer.namesrvAddr is required");
        }
        String groupName = mqProperty.getGroupName();
        if (isEmpty(groupName)) {
            throw new IllegalStateException("RocketMqProducer.groupName is required");
        }
        String topic = mqProperty.getTopic();
        String instanceName = mqProperty.getInstanceName();
        if (isEmpty(instanceName)) {
            instanceName = "producer-" + NUMBER.incrementAndGet() + "-" + currentTimeMillis();
        }
        producer = new DefaultMQProducer(groupName);
        producer.setInstanceName(instanceName);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
            start = true;
            logger.info("rocketMq producer namesrvAddr=[{}] groupName=[{}] topic=[{}] instanceName=[{}] 启动完成",
                    namesrvAddr, groupName, topic, instanceName);
        } catch (Exception e) {
            logger.warn("rocketMq producer namesrvAddr=[{}] groupName=[{}] topic=[{}] instanceName=[{}] 启动失败",
                    namesrvAddr, groupName, topic, instanceName);
            throw new IllegalStateException("mq producer init failed", e);
        }
    }

    @Override
    public boolean sendMessage(String topic, String tags, String keys, String body, Map<String, Object> ext) throws Exception {
        checkState(topic, "topic");
        Message message = new Message(topic, tags, keys, body.getBytes(UTF_8));
        SendResult result = null;
        long start = currentTimeMillis();
        try {
            result = producer.send(message);
        } catch (Exception e) {
            logger.error("发送异常 topic:[{}] tags:[{}] keys:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                    tags, keys, body, ext, currentTimeMillis() - start, e);
            throw e;
        }
        logger.info("发送完成 topic:[{}] tags:[{}] keys:[{}] result:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                tags, keys, result, body, ext, currentTimeMillis() - start);
        if (result == null || result.getSendStatus() != SendStatus.SEND_OK) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendMessage(String topic, String tags, String keys, String body) throws Exception {
        return sendMessage(topic, tags, keys, body, null);
    }

    @Override
    public boolean sendMessage(String topic, String tags, String body) throws Exception {
        return sendMessage(topic, tags, "", body);
    }

    @Override
    public boolean sendMessage(String topic, String body) throws Exception {
        return sendMessage(topic, "", body);
    }

    @Override
    public boolean sendOrderMessage(String topic, String tags, String keys, String body, Map<String, Object> ext) throws Exception {
        checkState(topic, "topic");
        checkState(keys, "keys");
        Message message = new Message(topic, tags, keys, body.getBytes(UTF_8));
        long start = currentTimeMillis();
        SendResult result = null;
        try {
            result = producer.send(message, (mqs, msg, arg) -> {
                int index = (mqs.size() - 1) & hash(arg);
                return mqs.get(index);
            }, keys);
        } catch (Exception e) {
            logger.error("发送异常 topic:[{}] tags:[{}] keys:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                    tags, keys, body, ext, currentTimeMillis() - start, e);
            throw e;
        }
        logger.info("发送完成 topic:[{}] tags:[{}] keys:[{}] result:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                tags, keys, result, body, ext, currentTimeMillis() - start);
        if (result == null || result.getSendStatus() != SendStatus.SEND_OK) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendOrderMessage(String topic, String tags, String keys, String body) throws Exception {
        return sendOrderMessage(topic, tags, keys, body, null);
    }

    @Override
    public boolean sendOrderMessage(String topic, String tags, String body) throws Exception {
        return sendOrderMessage(mqProperty.getTopic(), tags, "keys", body);
    }

    @Override
    public boolean sendOrderMessage(String topic, String body) throws Exception {
        return sendOrderMessage(mqProperty.getTopic(), "", body);
    }

    protected void checkState(String state, String field) {
        if (!start) {
            throw new IllegalStateException("producer not start");
        }
        if (isEmpty(state)) {
            throw new IllegalStateException(String.format("%s is required", field));
        }
    }

    @Override
    public boolean isStart() {
        return start;
    }

    @Override
    public void shutdown() {
        if (producer != null) {
            producer.shutdown();
            start = false;
            logger.info("the producer [{}] shutdown OK", this);
        }
    }

    private int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    /**
     * 获取配信息
     */
    public abstract RocketMqBaseProperty getMqProperty();
}
