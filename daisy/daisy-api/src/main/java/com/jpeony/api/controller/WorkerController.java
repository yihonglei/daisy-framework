package com.jpeony.api.controller;

import com.jpeony.common.utils.ThreadUtils;
import com.jpeony.common.utils.ApiResponse;
import com.jpeony.core.worker.CPUDemo1Worker;
import com.jpeony.core.worker.CPUDemo2Worker;
import com.jpeony.core.worker.IODemoWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步处理，工作线程测试
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {

    /**
     * orderNo主要用于测试日志追踪业务编号
     */
    @RequestMapping("/cpu1Demo")
    public ApiResponse cup1Demo(String bizContext, String orderNo) {
        ThreadUtils.submit(new CPUDemo1Worker(bizContext));

        return ApiResponse.success();
    }

    /**
     * orderNo主要用于测试日志追踪业务编号
     */
    @RequestMapping("/cpu2Demo")
    public ApiResponse cpu2Demo(String bizContext, String orderNo) {
        ThreadUtils.execute(new CPUDemo2Worker(bizContext));

        return ApiResponse.success();
    }

    /**
     * orderNo主要用于测试日志追踪业务编号
     */
    @RequestMapping("/ioDemo")
    public ApiResponse ioDemo(String bizContext, String orderNo) {
        ThreadUtils.executeStandard(new IODemoWorker(bizContext));

        return ApiResponse.success();
    }

}
