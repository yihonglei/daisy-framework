package com.jpeony.order.biz.handler;

import com.jpeony.commons.mq.producer.RabbitMessageProducer;
import com.jpeony.order.biz.context.CreateOrderContext;
import com.jpeony.order.biz.context.TransHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 利用rabbitmq发送延迟取消订单消息
 */
@Component
@Slf4j
public class SendMessageHandler extends AbstractTransHandler {
    @Autowired
    private RabbitMessageProducer rabbitMessageProducer;

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        CreateOrderContext createOrderContext = (CreateOrderContext) context;
        //将订单发送到rabbitmq
        try {
            rabbitMessageProducer.send(createOrderContext.getOrderId());
        } catch (Exception e) {
            log.error("发送订单id:{}到延迟队列失败", createOrderContext.getOrderId());
            return false;
        }
        return true;
    }
}