package com.jpeony.shopping.dto;

import com.jpeony.commons.result.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.shopping.constants.ShoppingRetCode;
import lombok.Data;

@Data
public class DeleteCheckedItemRequest extends AbstractRequest{

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
