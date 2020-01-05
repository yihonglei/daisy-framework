package com.lanhuigu.core.mq;

import com.lanhuigu.rocketmq.spring.annotation.RocketMqProducer;
import com.lanhuigu.rocketmq.spring.producer.AbstractRocketMqProducer;

/**
 * 消息发送
 * 业务使用注入对象：@Autowired private TestRocketMqProducer producer;
 * 调用发送方法：producer.sendMessage*
 * topic发送时如果不显示传入，则用配置文件里面的，一般进行显示传入!
 *
 * @author yihonglei
 */
@RocketMqProducer(
        namesrvAddr = "${rocketmq.producer.testProducer.namesrvAddr}",
        groupName = "${rocketmq.producer.testProducer.groupName}",
        topic = "${rocketmq.producer.testProducer.topic}")
public class TestRocketMqProducer extends AbstractRocketMqProducer {
}
