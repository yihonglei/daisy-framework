package com.jpeony.order.api.response;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoRes {
    private Integer orderId;
    private String orderName;
    private Integer status;
}
