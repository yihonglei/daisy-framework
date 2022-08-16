package com.jpeony.file.transfer.config;

import com.jpeony.file.transfer.factory.ConfigFactory;
import com.jpeony.file.transfer.client.FtpClient;
import com.jpeony.file.transfer.property.FtpUploadProperties;
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
 * ftp文件上传注入
 *
 * @author : yihonglei
 */
public class FtpUploadBuilder implements BeanFactoryPostProcessor, ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(FtpUploadBuilder.class);

    private ApplicationContext applicationContext;
    private FtpUploadProperties ftpUploadProperties;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
        this.ftpUploadProperties = ConfigFactory.getConfig(beanFactory, FtpUploadProperties.class, "file.upload.ftp");
        if (this.ftpUploadProperties == null) {
            logger.warn("ftp properties is not exist");
            return;
        }
        this.registryFtpUploadClient(beanFactory);
    }

    private void registryFtpUploadClient(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(FtpClient.class);
        beanDefinition.addConstructorArgValue(ftpUploadProperties);
        beanFactory.registerBeanDefinition(FtpClient.class.getName(), beanDefinition.getBeanDefinition());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
