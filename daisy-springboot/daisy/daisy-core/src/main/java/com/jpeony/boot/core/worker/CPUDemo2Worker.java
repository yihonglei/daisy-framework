package com.jpeony.boot.core.worker;

import com.jpeony.boot.common.concurrent.AbstractWorker;
import com.jpeony.boot.common.logback.LogTraceInfo;
import com.jpeony.boot.common.utils.SpringBeanUtils;
import com.jpeony.boot.core.service.CPUDemo2Service;
import com.jpeony.boot.core.service.cpu.CPUDemo2ServiceImpl;

/**
 * 异步业务处理
 *
 * @author yihonglei
 */
public class CPUDemo2Worker extends AbstractWorker {

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
    private CPUDemo2Service cpuDemo2Service;

    public CPUDemo2Worker(String bizContext) {
        this.bizContext = bizContext;
        logTraceInfo = LogTraceInfo.createByCurrentMDC();
        cpuDemo2Service = SpringBeanUtils.getBean(CPUDemo2ServiceImpl.class);
    }

    @Override
    protected LogTraceInfo getLogTraceInfo() {
        return this.logTraceInfo;
    }

    @Override
    protected void execute() {
        cpuDemo2Service.demo(bizContext);
    }
}
