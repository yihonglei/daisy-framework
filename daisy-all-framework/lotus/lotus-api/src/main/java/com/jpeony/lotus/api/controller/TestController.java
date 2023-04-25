package com.jpeony.lotus.api.controller;

import com.jpeony.lotus.common.utils.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层，service，请求规范，响应规范
 *
 * @author yihonglei
 */
@Validated
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public ApiResponse test() {
        return ApiResponse.success("daisy启动成功了!");
    }
}
