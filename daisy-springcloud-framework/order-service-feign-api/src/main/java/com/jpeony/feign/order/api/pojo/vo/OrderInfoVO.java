package com.jpeony.feign.order.api.pojo.vo;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoVO {
    private Integer orderId;
    private Integer status;
    private String name;
}
