package com.lanhuigu.api.controller;

import com.lanhuigu.common.concurrent.ThreadUtils;
import com.lanhuigu.common.utils.ApiResponse;
import com.lanhuigu.core.worker.CPUDemo1Worker;
import com.lanhuigu.core.worker.CPUDemo2Worker;
import com.lanhuigu.core.worker.IODemoWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步处理
 *
 * @author yihonglei
 * @date 2019/11/23 10:30 PM
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {

    @RequestMapping("/cpu1Demo")
    public ApiResponse cup1Demo(String bizContext) {
        ThreadUtils.submit(new CPUDemo1Worker(bizContext));

        return ApiResponse.success();
    }

    @RequestMapping("/cpu2Demo")
    public ApiResponse cpu2Demo(String bizContext) {
        ThreadUtils.execute(new CPUDemo2Worker(bizContext));

        return ApiResponse.success();
    }

    @RequestMapping("/ioDemo")
    public ApiResponse ioDemo(String bizContext) {
        ThreadUtils.executeStandard(new IODemoWorker(bizContext));

        return ApiResponse.success();
    }

}
