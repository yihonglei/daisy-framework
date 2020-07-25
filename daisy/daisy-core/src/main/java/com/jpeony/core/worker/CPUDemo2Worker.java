package com.jpeony.core.worker;

import com.jpeony.common.concurrent.AbstractWorker;
import com.jpeony.common.pojo.dto.TraceItemDTO;
import com.jpeony.common.util.SpringBeanUtils;
import com.jpeony.core.service.CPUDemo2Service;
import com.jpeony.core.service.cpu.CPUDemo2ServiceImpl;

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
    private TraceItemDTO traceItemDTO;
    /**
     * 业务处理
     */
    private CPUDemo2Service cpuDemo2Service;

    public CPUDemo2Worker(String bizContext) {
        this.bizContext = bizContext;
        traceItemDTO = TraceItemDTO.createByCurrentMDC();
        cpuDemo2Service = SpringBeanUtils.getBean(CPUDemo2ServiceImpl.class);
    }

    @Override
    protected TraceItemDTO getTraceItem() {
        return this.traceItemDTO;
    }

    @Override
    protected void execute() {
        cpuDemo2Service.demo(bizContext);
    }
}
