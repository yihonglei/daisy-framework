package com.lanhuigu.core.worker;

import com.lanhuigu.common.pojo.dto.TraceItemDTO;
import com.lanhuigu.common.spring.SpringBeanUtil;
import com.lanhuigu.common.worker.AbstractWorker;
import com.lanhuigu.core.service.IODemoService;
import com.lanhuigu.core.service.impl.IODemoServiceImpl;

/**
 * 异步业务处理
 *
 * @author yihonglei
 * @date 2019/11/22 1:49 PM
 */
public class IODemoWorker extends AbstractWorker {
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
    private IODemoService ioDemo1Service;

    public IODemoWorker(String bizContext) {
        this.bizContext = bizContext;
        traceItemDTO = TraceItemDTO.createByCurrentMDC();
        ioDemo1Service = SpringBeanUtil.getBean(IODemoServiceImpl.class);
    }

    @Override
    protected TraceItemDTO getTraceItem() {
        return this.traceItemDTO;
    }

    @Override
    protected void execute() {
        ioDemo1Service.demo(bizContext);
    }
}
