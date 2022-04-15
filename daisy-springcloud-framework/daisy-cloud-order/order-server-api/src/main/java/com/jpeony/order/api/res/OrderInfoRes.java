package com.jpeony.order.api.res;

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
