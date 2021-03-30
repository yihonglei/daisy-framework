package com.jpeony.commons.mq.producer;

import com.jpeony.commons.mq.config.RabbitMqConfig;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String context) {
        //将订单发送到rabbitmq
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE, context, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(10 * 60 * 1000);//毫秒为单位
            return message;
        });
    }
}