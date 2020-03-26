package com.jpeony.common.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 参数传递对象DTO
 *
 * @author yihonglei
 */
@Data
public class TestDTO {
    @NotBlank(message = "id不能为空")
    private Integer id;
    /**
     * 测试日志追踪业务编号
     */
    @NotBlank(message = "orderNo不能为空")
    private String orderNo;
}
