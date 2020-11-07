package com.jpeony.core.worker;

import com.jpeony.common.concurrent.AbstractWorker;
import com.jpeony.common.logback.LogTraceInfo;
import com.jpeony.common.utils.SpringBeanUtils;
import com.jpeony.core.service.IODemoService;
import com.jpeony.core.service.cpu.IODemoServiceImpl;

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
    private LogTraceInfo logTraceInfo;
    /**
     * 业务处理
     */
    private IODemoService ioDemo1Service;

    public IODemoWorker(String bizContext) {
        this.bizContext = bizContext;
        logTraceInfo = LogTraceInfo.createByCurrentMDC();
        ioDemo1Service = SpringBeanUtils.getBean(IODemoServiceImpl.class);
    }

    @Override
    protected LogTraceInfo getLogTraceInfo() {
        return this.logTraceInfo;
    }

    @Override
    protected void execute() {
        ioDemo1Service.demo(bizContext);
    }
}
