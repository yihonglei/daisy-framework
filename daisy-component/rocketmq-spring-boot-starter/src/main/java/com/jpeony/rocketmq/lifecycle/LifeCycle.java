package com.jpeony.rocketmq.lifecycle;

/**
 * 生命周期管理
 *
 * @author yihonglei
 */
public interface LifeCycle {
    /**
     * 启动
     */
    void start();

    /**
     * 是否启动 true表示启动 false表示未启动
     */
    boolean isStart();

    /**
     * 关闭
     */
    void shutdown();
}
