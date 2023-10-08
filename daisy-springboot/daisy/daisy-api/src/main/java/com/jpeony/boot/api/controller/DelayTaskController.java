package com.jpeony.boot.api.controller;

import com.jpeony.boot.common.utils.ApiResponse;
import com.jpeony.boot.core.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 延时任务
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/delay")
public class DelayTaskController {

    @Autowired
    private DelayService delayService;

    @GetMapping("/task")
    public ApiResponse delayTaskDemo() {
        delayService.delayTask(0, new Date());
        return ApiResponse.success();
    }
}
