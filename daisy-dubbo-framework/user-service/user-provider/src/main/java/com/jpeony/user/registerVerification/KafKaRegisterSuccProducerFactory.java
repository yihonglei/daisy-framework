package com.jpeony.user.registerVerification;

import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.util.Map;

/**
 * 自定义的生产者工厂，也可以不要这个类
 */
public class KafKaRegisterSuccProducerFactory extends DefaultKafkaProducerFactory {
    public KafKaRegisterSuccProducerFactory(Map confings) {
        super(confings);
    }
}
