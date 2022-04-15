package com.jpeony.boot.core.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 参数传递对象DTO
 *
 * @author yihonglei
 */
@Data
@ApiModel(value = "TestDTO", description = "请求参数")
public class TestDTO {
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "唯一ID")
    private Integer id;
    /**
     * 测试日志追踪业务编号
     */
    @NotBlank(message = "orderNo不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;
}
