package com.jpeony.order.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse extends AbstractResponse {

    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private BigDecimal price;

    private BigDecimal totalFee;

    private String picPath;

    private OrderDto orderDto;
}
