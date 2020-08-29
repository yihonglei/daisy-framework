package com.jpeony.core.mq;

import com.jpeony.rocketmq.spring.property.RocketMqBaseProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者配置
 *
 * @author yihonglei
 */
@Data
@ConfigurationProperties(prefix = "rocketmq.producer.test-producer")
@Configuration
public class TestRocketMqProducerProperty extends RocketMqBaseProperty {

}
