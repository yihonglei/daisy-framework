package com.jpeony.channel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yihonglei
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ChannelGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChannelGatewayApplication.class, args);
    }
}
