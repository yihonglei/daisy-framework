package com.jpeony.boot.core.worker;

import com.jpeony.boot.common.concurrent.AbstractCallableWorker;
import com.jpeony.boot.common.logback.LogTraceInfo;
import com.jpeony.boot.common.utils.SpringBeanUtils;
import com.jpeony.boot.core.service.CPUDemo1Service;
import com.jpeony.boot.core.service.cpu.CPUDemo1ServiceImpl;

/**
 * 异步业务处理
 *
 * @author yihonglei
 */
public class CPUDemo1Worker extends AbstractCallableWorker<Integer> {

    /**
     * 业务处理内容，可以是字符串或者对象等等
     */
    private String bizContext;

    /**
     * 日志追踪对象
     */
    private LogTraceInfo logTraceInfo;

    /**
     * 业务处理
     */
    private CPUDemo1Service cpuDemo1Service;

    public CPUDemo1Worker(String bizContext) {
        this.bizContext = bizContext;
        logTraceInfo = LogTraceInfo.createByCurrentMDC();
        cpuDemo1Service = SpringBeanUtils.getBean(CPUDemo1ServiceImpl.class);
    }

    @Override
    protected LogTraceInfo getLogTraceInfo() {
        return this.logTraceInfo;
    }

    @Override
    protected Integer execute() {
        return cpuDemo1Service.demo(bizContext);
    }

}
