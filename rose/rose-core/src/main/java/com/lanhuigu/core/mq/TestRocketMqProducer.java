package com.lanhuigu.core.mq;

import com.lanhuigu.rocketmq.spring.annotation.RocketMqProducer;
import com.lanhuigu.rocketmq.spring.producer.AbstractRocketMqProducer;

/**
 * @author yihonglei
 */
@RocketMqProducer(
        namesrvAddr = "rocketmq.producer.testProducer.namesrvAddr",
        groupName = "rocketmq.producer.testProducer.group",
        instanceName = "rocketmq.producer.testProducer.instanceName")
public class TestRocketProducer extends AbstractRocketMqProducer {
}
