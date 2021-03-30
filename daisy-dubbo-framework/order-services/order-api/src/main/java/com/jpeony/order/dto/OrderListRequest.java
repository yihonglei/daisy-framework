package com.jpeony.order.dto;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.order.constant.OrderRetCode;
import lombok.Data;

@Data
public class OrderListRequest extends AbstractRequest {

    private Long userId;

    private Integer page;

    private Integer size;

    private String sort;

    @Override
    public void requestCheck() {
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 5;
        }
        if (userId == null) {
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
