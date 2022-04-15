package com.jpeony.boot.api.controller;

import com.jpeony.boot.common.utils.ThreadUtils;
import com.jpeony.boot.common.utils.ApiResponse;
import com.jpeony.boot.core.worker.CPUDemo1Worker;
import com.jpeony.boot.core.worker.CPUDemo2Worker;
import com.jpeony.boot.core.worker.IODemoWorker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步处理，工作线程
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {

    /**
     * CPU 密集型线程池有返回值任务提交
     */
    @GetMapping("/cpu1Demo")
    public ApiResponse cup1Demo(String bizContext) {
        ThreadUtils.submit(new CPUDemo1Worker(bizContext));

        return ApiResponse.success();
    }

    /**
     * CPU 密集型线程池无返回值任务提交
     */
    @GetMapping("/cpu2Demo")
    public ApiResponse cpu2Demo(String bizContext) {
        ThreadUtils.execute(new CPUDemo2Worker(bizContext));

        return ApiResponse.success();
    }

    /**
     * IO 密集型线程池无返回值任务提交
     */
    @GetMapping("/ioDemo")
    public ApiResponse ioDemo(String bizContext) {
        ThreadUtils.executeStandard(new IODemoWorker(bizContext));

        return ApiResponse.success();
    }

}
