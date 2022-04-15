package com.jpeony.boot.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置
 *
 * @author yihonglei
 */
@Component
@ConfigurationProperties(prefix = "daisy")
@Data
public class DaisyConfig {
}
