package com.jpeony.boot.core.mq;

import com.jpeony.rocketmq.consumer.AbstractRocketMqConsumer;
import com.jpeony.rocketmq.property.RocketMqBaseProperty;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息消费者
 *
 * @author yihonglei
 */
@Component
public class TestRocketMqConsumer extends AbstractRocketMqConsumer {
    
    @Autowired
    private TestRocketMqConsumerProperty testRocketMqConsumerProperty;

//    @Override
//    public ConsumeOrderlyStatus handleOrderlyMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//        logger.info("TestRocketConsumer.ConsumeOrderlyStatus.handleOrderlyMessage:{}", context);
//        return ConsumeOrderlyStatus.SUCCESS;
//    }

    @Override
    public ConsumeConcurrentlyStatus handleMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        logger.info("TestRocketConsumer.ConsumeConcurrentlyStatus.handleMessage:{}", context);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public RocketMqBaseProperty getMqProperty() {
        RocketMqBaseProperty rocketMqBaseProperty = new RocketMqBaseProperty();
        BeanUtils.copyProperties(testRocketMqConsumerProperty, rocketMqBaseProperty);
        return rocketMqBaseProperty;
    }
}
