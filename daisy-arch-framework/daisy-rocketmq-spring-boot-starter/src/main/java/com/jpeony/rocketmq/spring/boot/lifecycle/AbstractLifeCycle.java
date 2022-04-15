package com.jpeony.rocketmq.spring.boot.lifecycle;

/**
 * @author yihonglei
 */
public abstract class AbstractLifeCycle implements LifeCycle {
    protected boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
