package com.jpeony.order.server.pojo.dto;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoDetailDTO {
    private Integer orderId;
    private String orderName;
    private Integer status;
}
