package com.jpeony.feign.order.api.pojo.vo;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoVO {
    private Integer orderId;
    private String orderName;
    private Integer status;
}
