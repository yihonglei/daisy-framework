package com.jpeony.shopping.form;

import com.jpeony.order.dto.OrderItemDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class OrderDetail {
    private String userName;
    private BigDecimal orderTotal;
    private long userId;
    private List<OrderItemDto> goodsList;
    private String tel;
    private String streetName;
}
