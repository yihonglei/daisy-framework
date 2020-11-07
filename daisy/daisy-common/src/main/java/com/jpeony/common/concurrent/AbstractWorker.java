package com.jpeony.common.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 没有返回值的业务工作线程
 *
 * @author yihonglei
 */
public abstract class AbstractWorker implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        TraceItem item = getTraceItem();
        try {
            item.putAll();
            execute();
        } catch (Throwable e) {
            logger.error("线程执行异常", e);
        } finally {
            item.removeAll();
        }
    }

    /**
     * 返回追踪日志信息
     */
    protected abstract TraceItem getTraceItem();

    /**
     * 执行线程方法
     */
    protected abstract void execute();
}
