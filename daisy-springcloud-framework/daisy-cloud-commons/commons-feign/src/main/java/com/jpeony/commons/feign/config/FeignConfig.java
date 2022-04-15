package com.jpeony.commons.feign.config;

import feign.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author yihonglei
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level level() {
        return Logger.Level.BASIC;
    }
}
