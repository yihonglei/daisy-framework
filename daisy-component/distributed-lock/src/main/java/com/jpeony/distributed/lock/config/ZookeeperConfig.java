package com.jpeony.distributed.lock.config;

import com.jpeony.distributed.lock.zookeeper.DistributedLockZookeeper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author yihonglei
 */
@SpringBootConfiguration
@ConditionalOnProperty(prefix = "lock", value = "enable", matchIfMissing = true)
public class ZookeeperConfig {
    @Bean
    public static DistributedLockZookeeper lockRedis() {
        return new DistributedLockZookeeper();
    }
}
