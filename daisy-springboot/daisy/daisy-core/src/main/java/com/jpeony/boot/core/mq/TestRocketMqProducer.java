package com.jpeony.boot.core.mq;

import com.jpeony.rocketmq.producer.AbstractRocketMqProducer;
import com.jpeony.rocketmq.property.RocketMqBaseProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送业务使用注入对象：@Autowired private TestRocketMqProducer producer
 * 调用发送方法：producer.sendMessage*
 * topic发送时如果不显示传入，则用配置文件里面的，一般进行显示传入!
 *
 * @author yihonglei
 */
@Component
public class TestRocketMqProducer extends AbstractRocketMqProducer {
    @Autowired
    private TestRocketMqProducerProperty testRocketMqProducerProperty;

    @Override
    public RocketMqBaseProperty getMqProperty() {
        RocketMqBaseProperty rocketMqBaseProperty = new RocketMqBaseProperty();
        BeanUtils.copyProperties(testRocketMqProducerProperty, rocketMqBaseProperty);
        return rocketMqBaseProperty;
    }
}
