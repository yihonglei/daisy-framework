package com.jpeony.commons.tool.zookeeperConfig;

import org.apache.curator.framework.CuratorFrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = ZooKeeperClientProperties.class)
@ConditionalOnClass(value = CuratorFrameworkFactory.class)
public class ZookeeperAutoConfiguration {
    public static final Logger logger = LoggerFactory.getLogger(CuratorFrameworkFactory.class);

    @Autowired
    ZooKeeperClientProperties zooKeeperClientProperties;

    @Bean
    public CuratorFrameworkClient createCuratorFrameworkClient(){
        logger.info(zooKeeperClientProperties.toString());
        return  new CuratorFrameworkClient(zooKeeperClientProperties);
    }
}
