package com.jpeony.core.worker;

import com.jpeony.common.pojo.dto.TraceItemDTO;
import com.jpeony.common.spring.SpringBeanUtil;
import com.jpeony.core.service.CPUDemo1Service;
import com.jpeony.core.service.impl.CPUDemo1ServiceImpl;

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
    private TraceItemDTO traceItemDTO;
    /**
     * 业务处理
     */
    private CPUDemo1Service cpuDemo1Service;

    public CPUDemo1Worker(String bizContext) {
        this.bizContext = bizContext;
        traceItemDTO = TraceItemDTO.createByCurrentMDC();
        cpuDemo1Service = SpringBeanUtil.getBean(CPUDemo1ServiceImpl.class);
    }

    @Override
    protected TraceItemDTO getTraceItem() {
        return this.traceItemDTO;
    }

    @Override
    protected Integer execute() {
        return cpuDemo1Service.demo(bizContext);
    }

}
