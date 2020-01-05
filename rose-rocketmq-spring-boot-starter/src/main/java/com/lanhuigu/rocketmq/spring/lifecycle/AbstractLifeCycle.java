package com.lanhuigu.rocketmq.spring.lifecycle;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author yihonglei
 */
public abstract class AbstractLifeCycle implements LifeCycle, EnvironmentAware {

    /**
     * 从spring配置中读取value值
     */
    protected String getValue(ConfigurableEnvironment env, String key) {
        return env.resolvePlaceholders(key);
    }

    /**
     * 判断字符串是否为空
     */
    protected boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
