package com.jpeony.core.mq;

import com.jpeony.rocketmq.spring.property.RocketMqBaseProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者配置
 *
 * @author yihonglei
 */
@Data
@ConfigurationProperties(prefix = "rocketmq.consumer.test-consumer")
@Configuration
public class TestRocketMqConsumerProperty extends RocketMqBaseProperty {

}
