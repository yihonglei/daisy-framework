package com.jpeony.api.controller;

import com.jpeony.common.util.ApiResponse;
import com.jpeony.core.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 延时任务测试
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/delay")
public class DelayTaskController {
    @Autowired
    private DelayService delayService;

    @RequestMapping("/task")
    public ApiResponse delayTaskDemo() {
        delayService.delayTask(0, new Date());
        return ApiResponse.success();
    }
}
