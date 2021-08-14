package com.jpeony.feign.order.api.request;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoParam {
    private String orderName;
    private Integer status;
}
