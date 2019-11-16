package com.lanhuigu.api.controller;

import com.lanhuigu.common.pojo.dto.TestDTO;
import com.lanhuigu.common.response.ApiResponse;
import com.lanhuigu.core.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author yihonglei
 * @date 2019/10/12 3:33 PM
 */
@Validated
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping("/queryTestById")
    public ApiResponse queryTestById(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestById(testDTO));
    }

    @RequestMapping("queryTestByIdXml")
    public ApiResponse queryTestByIdXml(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestByIdXml(testDTO));
    }
}
