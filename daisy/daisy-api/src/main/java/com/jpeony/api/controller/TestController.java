package com.jpeony.api.controller;

import com.jpeony.core.pojo.dto.TestDTO;
import com.jpeony.common.utils.ApiResponse;
import com.jpeony.core.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层，service，请求规范，响应规范测试
 *
 * @author yihonglei
 */
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "TestController", tags = {"查询用户相关"})
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public ApiResponse test() {
        return ApiResponse.success("daisy启动成功了!");
    }

    @ApiOperation(value = "查询用户信息", notes = "用户查询", response = ApiResponse.class)
    @PostMapping("/queryTestById")
    public ApiResponse queryTestById(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestById(testDTO));
    }

    @RequestMapping("queryTestByIdXml")
    public ApiResponse queryTestByIdXml(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestByIdXml(testDTO));
    }
}
