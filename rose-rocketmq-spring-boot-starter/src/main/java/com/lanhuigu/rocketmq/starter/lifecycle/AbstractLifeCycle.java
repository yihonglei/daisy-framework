package com.lanhuigu.rocketmq.starter.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象生命周期管理
 *
 * @author yihonglei
 */
public abstract class AbstractLifeCycle implements LifeCycle {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
