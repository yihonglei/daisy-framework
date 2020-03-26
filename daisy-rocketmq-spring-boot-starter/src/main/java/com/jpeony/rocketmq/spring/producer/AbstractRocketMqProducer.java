package com.jpeony.rocketmq.spring.producer;

import com.jpeony.rocketmq.spring.annotation.RocketMqProducer;
import com.jpeony.rocketmq.spring.lifecycle.AbstractLifeCycle;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;

/**
 * 抽象rocket-mq生产者
 *
 * @author yihonglei
 */
public abstract class AbstractRocketMqProducer extends AbstractLifeCycle implements Producer {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected DefaultMQProducer producer = null;
    private static AtomicInteger number = new AtomicInteger(0);
    protected ConfigurableEnvironment env;
    protected String topic;
    private String namesrvAddr;
    private String groupName;

    /**
     * 是否忽略日志
     */
    private boolean ignoreLog = false;

    /**
     * 是否开启
     */
    private volatile boolean start = false;

    @Override
    public void start() {
        synchronized (AbstractRocketMqProducer.class) {
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
        RocketMqProducer rocketMqProducer = this.getClass().getAnnotation(RocketMqProducer.class);
        if (rocketMqProducer == null) {
            throw new IllegalStateException("RocketMqProducer annotation is required");
        }
        namesrvAddr = getValue(env, rocketMqProducer.namesrvAddr());
        if (isEmpty(namesrvAddr)) {
            throw new IllegalStateException("RocketMqProducer.namesrvAddr is required");
        }
        groupName = getValue(env, rocketMqProducer.groupName());
        if (isEmpty(groupName)) {
            throw new IllegalStateException("RocketMqProducer.groupName is required");
        }
        this.topic = getValue(env, rocketMqProducer.topic());
        String instanceName = getValue(env, rocketMqProducer.instanceName());
        if (isEmpty(instanceName)) {
            instanceName = "producer-" + number.incrementAndGet() + "-" + currentTimeMillis();
        }
        String customizedTraceTopic = getValue(env, rocketMqProducer.customizedTraceTopic());
        if (isEmpty(customizedTraceTopic)) {
            customizedTraceTopic = null;
        }
        int sendMsgTimeout = rocketMqProducer.sendMsgTimeout();
        if (rocketMqProducer.sendMsgTimeout() <= 0) {
            sendMsgTimeout = RocketMqProducer.SEND_MSG_TIMEOUT;
        }
        int retryTimesWhenSendFailed = rocketMqProducer.retryTimesWhenSendFailed();
        if (retryTimesWhenSendFailed < 0) {
            retryTimesWhenSendFailed = RocketMqProducer.RETRY_TIMES_WHEN_SEND_FAILED;
        }
        int defaultTopicQueueNums = rocketMqProducer.defaultTopicQueueNums();
        if (defaultTopicQueueNums <= 0) {
            defaultTopicQueueNums = RocketMqProducer.TOPIC_QUEUE_NUMS;
        }
        ignoreLog = rocketMqProducer.ignoreLog();
        producer = new DefaultMQProducer(groupName, rocketMqProducer.enableMsgTrace(), customizedTraceTopic);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setInstanceName(instanceName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setDefaultTopicQueueNums(defaultTopicQueueNums);
        try {
            producer.start();
            start = true;
            logger.info("rocketMq producer [namesrvAddr={} groupName={} topic={} instanceName={}] 启动完成",
                    namesrvAddr, groupName, topic, instanceName);
        } catch (Exception e) {
            logger.warn("rocketMq producer [namesrvAddr={} groupName={} topic={} instanceName={}] 启动失败",
                    namesrvAddr, groupName, topic, instanceName);
            throw new IllegalStateException("mq producer init fail", e);
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
            if (!ignoreLog) {
                logger.error("发送异常topic:[{}] tags:[{}] keys:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                        tags, keys, body, ext, currentTimeMillis() - start, e);
            }
            throw e;
        }
        if (!ignoreLog) {
            logger.info("发送完成topic:[{}] tags:[{}] keys:[{}] result:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                    tags, keys, result, body, ext, currentTimeMillis() - start);
        }
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
            if (!ignoreLog) {
                logger.error("发送异常topic:[{}] tags:[{}] keys:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                        tags, keys, body, ext, currentTimeMillis() - start, e);
            }
            throw e;
        }
        if (!ignoreLog) {
            logger.info("发送完成topic:[{}] tags:[{}] keys:[{}] result:[{}] body:[{}] ext:[{}] 耗时:[{}]ms", topic,
                    tags, keys, result, body, ext, currentTimeMillis() - start);
        }
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
        return sendOrderMessage(this.topic, tags, "keys", body);
    }

    @Override
    public boolean sendOrderMessage(String topic, String body) throws Exception {
        return sendOrderMessage(this.topic, "", body);
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

    @Override
    public void setEnvironment(Environment environment) {
        this.env = (ConfigurableEnvironment) environment;
    }

    private int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
}
