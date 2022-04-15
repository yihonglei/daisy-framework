package com.jpeony.boot.api.controller;

import com.jpeony.boot.core.pojo.dto.TestDTO;
import com.jpeony.boot.common.utils.ApiResponse;
import com.jpeony.boot.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public ApiResponse test() {
        return ApiResponse.success("daisy启动成功了!");
    }

    @PostMapping("/queryTestById")
    public ApiResponse queryTestById(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestById(testDTO));
    }

    @PostMapping("queryTestByIdXml")
    public ApiResponse queryTestByIdXml(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestByIdXml(testDTO));
    }
}
