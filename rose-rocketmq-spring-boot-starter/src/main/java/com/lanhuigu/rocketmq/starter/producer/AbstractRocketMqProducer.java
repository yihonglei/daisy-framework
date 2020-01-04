package com.lanhuigu.rocketmq.starter.producer;


import com.lanhuigu.rocketmq.starter.annotation.RocketMqProducer;
import com.lanhuigu.rocketmq.starter.lifecycle.AbstractLifeCycle;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * @author yihonglei
 */
public abstract class AbstractRocketMqProducer extends AbstractLifeCycle {

    protected DefaultMQProducer producer;

    private static volatile boolean isStart = false;
    @Autowired
    private Environment env;
    private String topic;
    private String namesrvAddr;
    private String system;
    private String tag;
    private boolean ignoreLog = false;

    @Override
    public void start() {
        init();
    }

    @Override
    public boolean isStart() {
        return isStart;
    }

    @Override
    public void shutdown() {
        if (producer != null) {
            logger.warn("shutdown class:[{}]", this);
            producer.shutdown();
        }
    }

    /**
     * 初始化
     */
    protected void init() {
        RocketMqProducer config = this.getClass().getAnnotation(RocketMqProducer.class);
        if (config == null) {
            logger.warn("未找到消费参数 RocketMqProducer is null");
            return;
        }
        String namesrvAddr = env.getProperty(config.namesrvAddr());
        Assert.isTrue(StringUtils.isNotBlank(namesrvAddr), "namesrvAddr must not be null");
        this.namesrvAddr = namesrvAddr;
        String groupName = env.getProperty(config.groupName());
        Assert.isTrue(StringUtils.isNotBlank(groupName), "groupName must not be null");
        String topic = env.getProperty(config.topic());
        if (StringUtils.isBlank(topic)) {
            topic = topic();
        }
        this.topic = topic;
        this.tag = env.getProperty(config.tag());
        ignoreLog = config.ignoreLog();
        String instanceName = env.getProperty(config.instanceName());
        if (StringUtils.isBlank(instanceName)) {
            instanceName = "producer_" + System.currentTimeMillis();
        }
        this.system = env.getProperty("spring.lanhuigu.instance", "defaultRocketMq");
        producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        int retryTimesWhenSendFailed = config.retryTimesWhenSendFailed();
        if (retryTimesWhenSendFailed <= 0) {
            retryTimesWhenSendFailed = 3;
        }
        int sendMsgTimeout = config.sendMsgTimeout();
        if (sendMsgTimeout <= 0) {
            sendMsgTimeout = 3 * 1000;
        }
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setSendMsgTimeout(sendMsgTimeout);
        try {
            producer.start();
            logger.info("rocketMq producer [namesrvAddr={} groupName={} topic={} tag={} instanceName={} "
                            + "sendMsgTimeout={} retryTimesWhenSendFailed={}] 启动完成",
                    namesrvAddr, groupName, topic, tag, instanceName, sendMsgTimeout, retryTimesWhenSendFailed);
        } catch (MQClientException e) {
            logger.error("rocketMq producer [namesrvAddr={} groupName={} topic={} tag={} instanceName={} "
                            + "sendMsgTimeout={} retryTimesWhenSendFailed={}] 启动失败",
                    namesrvAddr, groupName, topic, tag, instanceName, sendMsgTimeout, retryTimesWhenSendFailed, e);
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 发送消息顺序消息
     */
    public boolean sendMessageOrderly(String topic, String tag, String key, String value) {
        Message msg = new Message(topic, tag, key, value.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg, (mqs, message, arg) -> {
                int index = mqs.size() - 1 & AbstractRocketMqProducer.hash(arg);
                return mqs.get(index);
            }, key);
        } catch (Throwable e) {
            logger.error("发送失败 sendResult:[{}] topic:[{}] tag:[{}] key:[{}] value:[{}]", sendResult, topic, tag, key, value, e);
            return false;
        }
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            logger.warn("发送失败 sendResult:[{}] topic:[{}] tag:[{}] key:[{}] value:[{}]", sendResult, topic, tag, key, value);
            return false;
        }
        if (!ignoreLog) {
            logger.info("发送成功 sendResult:[{}] topic:[{}] tag:[{}] key:[{}] value:[{}]", sendResult, topic, tag, key, value);
        }
        return true;
    }

    /**
     * 发送消息顺序消息
     */
    public boolean sendMessageOrderlyWithTopic(String tag, String key, String value) {
        if (StringUtils.isBlank(topic)) {
            throw new IllegalArgumentException("topic must not be null");
        }
        return sendMessageOrderly(topic, tag, key, value);
    }

    /**
     * 发送消息顺序消息
     */
    public boolean sendMessageOrderly(String topic, String key, String value) {
        return sendMessageOrderly(topic, null, key, value);
    }

    /**
     * 发送消息顺序消息
     */
    public boolean sendMessageOrderlyWithTag(String key, String value) {
        return sendMessageOrderlyWithTopic(tag, key, value);
    }

    /**
     * 发送消息普通消息
     */
    public boolean sendMessage(String topic, String tag, String value) {
        Message msg = new Message(topic, tag, value.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (Throwable e) {
            logger.error("发送失败 sendStatus:[{}] topic:[{}] tag:[{}] value:[{}]", sendResult == null ? sendResult : sendResult.getSendStatus(), topic, tag, value, e);
            return false;
        }
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            logger.warn("发送失败 sendStatus:[{}] topic:[{}] tag:[{}] value:[{}]", sendResult == null ? sendResult : sendResult.getSendStatus(), topic, tag, value);
            return false;
        }
        if (!ignoreLog) {
            logger.info("发送成功 topic:[{}] tag:[{}] value:[{}]", topic, tag, value);
        }
        return true;
    }

    /**
     * 发送消息普通消息
     */
    public boolean sendMessageWithTopic(String tag, String value) {
        return sendMessage(topic, tag, value);
    }

    /**
     * 发送消息普通消息
     */
    public boolean sendMessage(String topic, String value) {
        return sendMessage(topic, null, value);
    }

    /**
     * 发送消息普通消息
     */
    public boolean sendMessageWithTag(String value) {
        return sendMessage(topic, tag, value);
    }

    private static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    /**
     * topic名称
     */
    protected String topic() {
        return null;
    }
}
