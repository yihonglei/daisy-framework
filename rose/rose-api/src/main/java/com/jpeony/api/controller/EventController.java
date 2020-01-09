package com.jpeony.api.controller;

import com.jpeony.common.event.RoseEvent;
import com.jpeony.common.event.RoseEventEnum;
import com.jpeony.common.event.RoseEventManager;
import com.jpeony.common.util.ApiResponse;
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

    @RequestMapping("/eventDemo")
    public ApiResponse eventDemo(String userName) {
        // 事件发布，支持任意参数，可以是一个值，也可以传入对象
        RoseEventManager.fireEvent(new RoseEvent(RoseEventEnum.TEST_EVENT, userName));
        return ApiResponse.success();
    }
}
