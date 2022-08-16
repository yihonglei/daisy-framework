package com.jpeony.file.transfer.config;

import com.jpeony.file.transfer.property.FtpUploadProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ftp 属性注入
 *
 * @author : yihonglei
 */
@Configuration
@ConditionalOnProperty(prefix = "file.upload.ftp", name = "enable", havingValue = "true")
@ConditionalOnClass(FtpUploadProperties.class)
public class FtpAutoConfiguration {

    @ConditionalOnExpression(value = "!''.equals('${file.upload.ftp.host:}')")
    @ConditionalOnMissingBean(FtpUploadBuilder.class)
    @Bean
    public static FtpUploadBuilder ftpUploadBuilder() {
        return new FtpUploadBuilder();
    }
}
