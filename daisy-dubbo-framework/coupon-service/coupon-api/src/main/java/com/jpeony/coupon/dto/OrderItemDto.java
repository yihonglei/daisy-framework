package com.jpeony.coupon.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDto implements Serializable {
    private String orderId;
    private BigDecimal price;
    private Integer num;
}
