package com.jpeony.file.transfer.config;

import com.jpeony.file.transfer.property.SeaweedUploadProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件工具自动配置
 *
 * @author : yihonglei
 */
@Configuration
@ConditionalOnProperty(prefix = "file.upload.seaweed", name = "enable", havingValue = "true")
@ConditionalOnClass(SeaweedUploadProperties.class)
public class SeaweedAutoConfiguration {

    @ConditionalOnExpression(value = "!''.equals('${file.upload.seaweed.host:}')")
    @ConditionalOnMissingBean({SeaweedUploadBuilder.class})
    @Bean
    public static SeaweedUploadBuilder seaweedUploadBuilder() {
        return new SeaweedUploadBuilder();
    }
}
