package com.lanhuigu.core.worker;

import com.lanhuigu.common.pojo.dto.TraceItemDTO;
import com.lanhuigu.common.spring.SpringBeanUtil;
import com.lanhuigu.common.worker.AbstractWorker;
import com.lanhuigu.core.service.CPUDemo2Service;
import com.lanhuigu.core.service.impl.CPUDemo2ServiceImpl;

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
        cpuDemo2Service = SpringBeanUtil.getBean(CPUDemo2ServiceImpl.class);
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
