package com.jpeony.coupon.dto;

import com.jpeony.commons.core.AbstractRequest;
import lombok.Data;

import java.util.List;

@Data
public class UseCouponRequest extends AbstractRequest {
    private List<OrderItemDto> orderItemList;

    @Override
    public void requestCheck() {

    }
}
