package com.jpeony.boot.core.worker;

import com.jpeony.boot.common.concurrent.AbstractWorker;
import com.jpeony.boot.common.logback.LogTraceInfo;
import com.jpeony.boot.common.utils.SpringBeanUtils;
import com.jpeony.boot.core.service.IODemoService;
import com.jpeony.boot.core.service.cpu.IODemoServiceImpl;

/**
 * @author yihonglei
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
