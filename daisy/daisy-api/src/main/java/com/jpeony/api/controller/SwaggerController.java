package com.jpeony.api.controller;

import com.jpeony.common.utils.ApiResponse;
import com.jpeony.core.pojo.dto.TestDTO;
import com.jpeony.core.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger测试
 *
 * @author yihonglei
 */
@Validated
@RestController
@RequestMapping("swagger")
@Api(value = "SwaggerController", tags = {"Swagger相关"})
public class SwaggerController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "查询用户信息", notes = "用户查询", response = ApiResponse.class)
    @PostMapping("/queryTestById")
    public ApiResponse queryTestById(TestDTO testDTO) {
        return ApiResponse.success(testService.queryTestById(testDTO));
    }
}
