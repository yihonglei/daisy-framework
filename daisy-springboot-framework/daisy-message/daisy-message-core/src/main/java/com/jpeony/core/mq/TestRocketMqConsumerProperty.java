package com.jpeony.core.mq;

import com.jpeony.rocketmq.spring.property.RocketMqBaseProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者配置
 *
 * @author yihonglei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(prefix = "rocketmq.consumer.test-consumer")
@Configuration
public class TestRocketMqConsumerProperty extends RocketMqBaseProperty {

}
