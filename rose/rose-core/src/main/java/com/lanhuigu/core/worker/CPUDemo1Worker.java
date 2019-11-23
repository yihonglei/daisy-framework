package com.lanhuigu.core.worker;

import com.lanhuigu.common.pojo.dto.TraceItemDTO;
import com.lanhuigu.common.spring.SpringBeanUtil;
import com.lanhuigu.common.worker.AbstractCallableWorker;
import com.lanhuigu.core.service.CPUDemo1Service;
import com.lanhuigu.core.service.impl.CPUDemo1ServiceImpl;

/**
 * Demo：异步发送短信
 *
 * @author yihonglei
 * @date 2019/11/22 1:40 PM
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
