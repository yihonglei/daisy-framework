package com.jpeony.boot.core.mq;

import com.jpeony.rocketmq.spring.boot.property.RocketMqBaseProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者配置
 *
 * @author yihonglei
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(prefix = "rocketmq.producer.test-producer")
@Configuration
public class TestRocketMqProducerProperty extends RocketMqBaseProperty {

}
