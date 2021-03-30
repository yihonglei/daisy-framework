package com.jpeony.shopping.dto;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.shopping.constants.ShoppingRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CheckAllItemRequest extends AbstractRequest {
    private Long userId;
    private String checked;

    @Override
    public void requestCheck() {
        if(userId==null|| StringUtils.isBlank(checked)){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
