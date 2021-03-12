package com.jpeony.daisy.cloud.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages = {"com.bootdo.*.dao"})
@SpringBootApplication
public class DaisyCloudBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaisyCloudBaseApplication.class, args);
    }
}
