package com.jpeony.dubbo.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yihonglei
 */
@SpringBootApplication(scanBasePackages = "com.jpeony.dubbo.user.*")
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
