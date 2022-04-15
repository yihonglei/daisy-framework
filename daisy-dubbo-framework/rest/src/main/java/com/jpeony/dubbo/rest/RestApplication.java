package com.jpeony.dubbo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yihonglei
 */
@SpringBootApplication(scanBasePackages = "com.jpeony.dubbo.rest.*")
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
