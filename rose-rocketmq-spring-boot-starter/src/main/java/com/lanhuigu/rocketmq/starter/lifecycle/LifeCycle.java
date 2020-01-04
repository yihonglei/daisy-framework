package com.lanhuigu.rocketmq.starter.lifecycle;

/**
 * 生命周期
 *
 * @author yihonglei
 */
public interface LifeCycle {

    /**
     * 启动
     */
    void start();

    /**
     * 是否启动
     *
     * @return true表示启动成功 false表示启动失败
     */
    boolean isStart();

    /**
     * 关闭
     */
    void shutdown();
}
