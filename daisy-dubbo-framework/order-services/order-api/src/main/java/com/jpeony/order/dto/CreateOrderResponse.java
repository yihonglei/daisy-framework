package com.jpeony.order.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

@Data
public class CreateOrderResponse extends AbstractResponse{

    private String orderId;
}
