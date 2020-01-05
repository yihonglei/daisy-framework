package com.lanhuigu.core.mq;

import com.lanhuigu.rocketmq.spring.annotation.RocketMqConsumer;
import com.lanhuigu.rocketmq.spring.consumer.AbstractRocketMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author yihonglei
 */
@RocketMqConsumer(
        namesrvAddr = "rocketmq.consumer.testConsumer.namesrvAddr",
        topic = "rocketmq.consumer.testConsumer.topic",
        tag = "rocketmq.consumer.testConsumer.tag",
        groupName = "rocketmq.consumer.testConsumer.group",
        instanceName = "rocketmq.consumer.testConsumer.instanceName")
public class TestRocketConsumer extends AbstractRocketMqConsumer {
    @Override
    public ConsumeOrderlyStatus handleMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        logger.info("TestRocketConsumer.ConsumeOrderlyStatus.handleMessage:{}", context);
        return ConsumeOrderlyStatus.SUCCESS;
    }

    @Override
    public ConsumeConcurrentlyStatus handleMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        logger.info("TestRocketConsumer.ConsumeConcurrentlyStatus.handleMessage:{}", context);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
