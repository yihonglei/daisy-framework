package com.jpeony.api.controller;

import com.jpeony.common.pojo.dto.TestDTO;
import com.jpeony.common.pojo.vo.TestVO;
import com.jpeony.common.util.DaisyResponse;
import com.jpeony.core.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @PostMapping("/queryTestById")
    @ApiOperation(value = "查询用户信息", notes = "用户查询", response = DaisyResponse.class)
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作失败"),
            @ApiResponse(code = 1, message = "操作成功", response = TestVO.class),
    })
    public DaisyResponse queryTestById(TestDTO testDTO) {
        return DaisyResponse.success(testService.queryTestById(testDTO));
    }

    @RequestMapping("queryTestByIdXml")
    public DaisyResponse queryTestByIdXml(TestDTO testDTO) {
        return DaisyResponse.success(testService.queryTestByIdXml(testDTO));
    }
}
