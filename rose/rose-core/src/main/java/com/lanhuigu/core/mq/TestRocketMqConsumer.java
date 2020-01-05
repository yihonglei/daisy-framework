package com.lanhuigu.core.mq;

import com.lanhuigu.rocketmq.spring.annotation.RocketMqConsumer;
import com.lanhuigu.rocketmq.spring.annotation.RocketMqProducer;
import com.lanhuigu.rocketmq.spring.consumer.AbstractRocketMqConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消息消费者
 *
 * @author yihonglei
 */
@RocketMqConsumer(
        namesrvAddr = "${rocketmq.consumer.testConsumer.namesrvAddr}",
        groupName = "${rocketmq.consumer.testConsumer.groupName}",
        topic = "${rocketmq.consumer.testConsumer.topic}",
        tag = "${rocketmq.consumer.testConsumer.tag}")
public class TestRocketMqConsumer extends AbstractRocketMqConsumer {
    @Override
    public ConsumeOrderlyStatus handleOrderlyMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        logger.info("TestRocketConsumer.ConsumeOrderlyStatus.handleOrderlyMessage:{}", context);
        return ConsumeOrderlyStatus.SUCCESS;
    }

    @Override
    public ConsumeConcurrentlyStatus handleMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        logger.info("TestRocketConsumer.ConsumeConcurrentlyStatus.handleMessage:{}", context);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
