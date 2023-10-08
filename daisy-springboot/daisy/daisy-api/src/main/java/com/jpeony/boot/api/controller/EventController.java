package com.jpeony.boot.api.controller;

import com.jpeony.boot.common.event.DaisyEvent;
import com.jpeony.boot.common.event.DaisyEventEnum;
import com.jpeony.boot.common.event.DaisyEventManager;
import com.jpeony.boot.common.utils.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事件机制使用测试，观察者模式使用，一个事件发布，相关监听能够收到
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @GetMapping("/eventDemo")
    public ApiResponse eventDemo(String userName) {
        // 事件发布，支持任意参数，可以是一个值，也可以传入对象
        DaisyEventManager.fireEvent(new DaisyEvent(DaisyEventEnum.TEST_EVENT, userName));
        return ApiResponse.success();
    }
}
