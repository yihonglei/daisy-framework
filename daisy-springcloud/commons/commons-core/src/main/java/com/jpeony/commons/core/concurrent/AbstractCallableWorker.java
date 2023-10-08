package com.jpeony.commons.core.concurrent;

import com.jpeony.commons.core.logback.LogTraceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 带有返回值的业务工作线程
 *
 * @author yihonglei
 */
public abstract class AbstractCallableWorker<V> implements Callable<V> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public V call() {
        LogTraceInfo traceInfo = getLogTraceInfo();
        try {
            traceInfo.putAll();
            return execute();
        } catch (Throwable e) {
            logger.error("线程运行异常", e);
        } finally {
            traceInfo.removeAll();
        }

        return null;
    }

    /**
     * 返回追踪日志信息
     */
    protected abstract LogTraceInfo getLogTraceInfo();

    /**
     * 执行线程
     */
    protected abstract V execute();
}
