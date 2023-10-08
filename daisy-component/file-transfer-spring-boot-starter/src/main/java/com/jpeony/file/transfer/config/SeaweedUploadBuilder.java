package com.jpeony.file.transfer.config;

import com.jpeony.file.transfer.client.SeaweedFsClient;
import com.jpeony.file.transfer.factory.ConfigFactory;
import com.jpeony.file.transfer.property.SeaweedUploadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * seaweed文件上传注入
 *
 * @author : yihonglei
 */
public class SeaweedUploadBuilder implements BeanFactoryPostProcessor, ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(SeaweedUploadBuilder.class);

    private ApplicationContext applicationContext;
    private SeaweedUploadProperties seaweedUploadProperties;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
        this.seaweedUploadProperties = ConfigFactory.getConfig(beanFactory, SeaweedUploadProperties.class, "file.upload.seaweed");
        if (this.seaweedUploadProperties == null) {
            logger.warn("seaweed properties is not exist");
            return;
        }
        this.registrySeaweedUploadClient(beanFactory);
    }

    private void registrySeaweedUploadClient(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(SeaweedFsClient.class);
        beanDefinition.addConstructorArgValue(seaweedUploadProperties);
        beanFactory.registerBeanDefinition(SeaweedFsClient.class.getName(), beanDefinition.getBeanDefinition());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
