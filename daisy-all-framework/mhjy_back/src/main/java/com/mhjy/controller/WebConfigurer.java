package com.mhjy.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Value("${CROS_MAPPING}")
    private String mapping;

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;

    @Value("${CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping(mapping)
                .allowCredentials(true)
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
