package com.jpeony.boot.core.worker;

import com.jpeony.boot.common.concurrent.AbstractWorker;
import com.jpeony.boot.common.logback.LogTraceInfo;
import com.jpeony.boot.common.utils.SpringBeanUtils;
import com.jpeony.boot.core.service.DelayService;
import com.jpeony.boot.core.service.delay.DelayServiceImpl;

import java.util.Date;

/**
 * 延时任务
 *
 * @author yihonglei
 */
public class DelayTaskWorker extends AbstractWorker {

    private long passTime;

    private Date startTime;

    /**
     * 日志追踪对象
     */
    private LogTraceInfo logTraceInfo;

    private DelayService delayService;

    public DelayTaskWorker(long passTime, Date startTime) {
        this.passTime = passTime;
        this.startTime = startTime;
        logTraceInfo = LogTraceInfo.createByCurrentMDC();
        delayService = SpringBeanUtils.getBean(DelayServiceImpl.class);
    }

    /**
     * ignore
     */
    @Override
    protected LogTraceInfo getLogTraceInfo() {
        return this.logTraceInfo;
    }

    /**
     * 模拟每个3秒执行一次任务，超过20秒后自动退出任务
     */
    @Override
    protected void execute() {
        delayService.delayTask(passTime, startTime);
    }
}
