package com.jpeony.order.dto;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.order.constant.OrderRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CancelOrderRequest extends AbstractRequest{

    private String orderId;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(orderId)){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
