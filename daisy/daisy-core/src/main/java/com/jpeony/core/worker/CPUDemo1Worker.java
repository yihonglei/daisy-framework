package com.jpeony.core.worker;

import com.jpeony.common.concurrent.AbstractCallableWorker;
import com.jpeony.common.concurrent.TraceItem;
import com.jpeony.common.utils.SpringBeanUtils;
import com.jpeony.core.service.CPUDemo1Service;
import com.jpeony.core.service.cpu.CPUDemo1ServiceImpl;

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
    private TraceItem traceItem;
    /**
     * 业务处理
     */
    private CPUDemo1Service cpuDemo1Service;

    public CPUDemo1Worker(String bizContext) {
        this.bizContext = bizContext;
        traceItem = TraceItem.createByCurrentMDC();
        cpuDemo1Service = SpringBeanUtils.getBean(CPUDemo1ServiceImpl.class);
    }

    @Override
    protected TraceItem getTraceItem() {
        return this.traceItem;
    }

    @Override
    protected Integer execute() {
        return cpuDemo1Service.demo(bizContext);
    }

}
