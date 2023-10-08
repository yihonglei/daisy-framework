package com.jpeony.redis.config;

import com.jpeony.redis.client.RedisClient;
import com.jpeony.redis.exception.RedisAutoConfigException;
import com.jpeony.redis.property.ClusterProperties;
import com.jpeony.redis.property.RedisClusterPropertiesFactory;
import com.jpeony.redis.property.RedisProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * RedisClientBuilder：
 * 1、读取集群配置
 * 2、根据配置实例化 RedisClient
 * 3、注册 RedisClient 的多个实例到 beanFactory
 *
 * @author yihonglei
 */
public class RedisClientBuilder implements BeanFactoryPostProcessor, ApplicationContextAware {

    private ClusterProperties clusterProperties;

    private ApplicationContext applicationContext;

    private static final String DEFAULT_CLUSTER_BEAN_NAME = "redisSupport";

    public RedisClientBuilder() {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
        this.clusterProperties = RedisClusterPropertiesFactory.getProperties(beanFactory, "redis");
        if (this.clusterProperties == null) {
            throw new RedisAutoConfigException("Redis cluster property file does not exist.");
        } else {
            this.registryRedisClientBeans(beanFactory);
        }
    }

    private void registryRedisClientBeans(DefaultListableBeanFactory beanFactory) {
        this.clusterProperties.getClusters().forEach(p -> registryBean(beanFactory, p));
    }

    private void registryBean(DefaultListableBeanFactory beanFactory, RedisProperties properties) {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(RedisClient.class);
        beanDefinition.addConstructorArgValue(properties);
        beanDefinition.addConstructorArgValue(applicationContext.getEnvironment().getProperty("spring.jpeony.instance", "defaultRedis"));
        String beanName = properties.getName();
        if (StringUtils.isEmpty(beanName)) {
            beanName = DEFAULT_CLUSTER_BEAN_NAME;
        }
        beanFactory.registerBeanDefinition(beanName, beanDefinition.getBeanDefinition());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
