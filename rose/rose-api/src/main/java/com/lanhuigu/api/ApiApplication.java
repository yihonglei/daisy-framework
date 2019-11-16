package com.lanhuigu.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author yihonglei
 * @date 2019/11/16 10:57 AM
 */
@SpringBootApplication(scanBasePackages = "com.lanhuigu")
@MapperScan("com.lanhuigu.core.mapper")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
