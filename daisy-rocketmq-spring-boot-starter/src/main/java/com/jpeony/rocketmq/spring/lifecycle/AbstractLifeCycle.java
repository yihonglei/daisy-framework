package com.jpeony.rocketmq.spring.lifecycle;

/**
 * @author yihonglei
 */
public abstract class AbstractLifeCycle implements LifeCycle {
    /**
     * 判断字符串是否为空
     */
    protected boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
