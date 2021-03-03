package com.jpeony.redis.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/***
 * 当属性: redis.enable=false 时关闭
 *
 * @author yihonglei
 */
@SpringBootConfiguration
@ConditionalOnProperty(prefix = "redis", value = "enable", matchIfMissing = true)
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(RedisClientBuilder.class)
    public static RedisClientBuilder redisClientBuilder() {
        return new RedisClientBuilder();
    }
}
