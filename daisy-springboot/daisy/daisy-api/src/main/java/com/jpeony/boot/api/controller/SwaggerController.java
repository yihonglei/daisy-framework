package com.jpeony.boot.api.controller;

import com.jpeony.boot.common.utils.ApiResponse;
import com.jpeony.boot.core.pojo.dto.TestDTO;
import com.jpeony.boot.core.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger API 接口文档
 *
 * @author yihonglei
 */
@Validated
@RestController
@RequestMapping("/swagger/test")
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
