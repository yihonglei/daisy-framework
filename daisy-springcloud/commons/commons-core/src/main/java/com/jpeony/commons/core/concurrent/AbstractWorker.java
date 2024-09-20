package com.jpeony.commons.core.concurrent;


import com.jpeony.commons.core.logback.LogTraceInfo;
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
        LogTraceInfo traceInfo = getLogTraceInfo();
        try {
            traceInfo.putAll();
            execute();
        } catch (Throwable e) {
            logger.error("线程执行异常", e);
        } finally {
            traceInfo.removeAll();
        }
    }

    /**
     * 返回追踪日志信息
     */
    protected abstract LogTraceInfo getLogTraceInfo();

    /**
     * 执行线程方法
     */
    protected abstract void execute();
}
