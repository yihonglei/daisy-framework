package com.jpeony.order.api.request;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoParam {
    private String orderName;
    private Integer status;
}
