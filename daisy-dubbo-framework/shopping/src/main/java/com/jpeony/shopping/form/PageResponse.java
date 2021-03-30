package com.jpeony.shopping.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PageResponse {

    @ApiModelProperty(name = "data", value = "数据信息")
    private Object data;

    @ApiModelProperty(name = "total", value = "数据条数", example = "10")
    private Long total;
}
