package com.jpeony.shopping.utils;

import com.jpeony.commons.result.AbstractResponse;
import com.jpeony.commons.tool.exception.ExceptionUtil;
import com.jpeony.shopping.constants.ShoppingRetCode;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e) {
        try {
            ExceptionUtil.handlerException4biz(response, e);
        } catch (Exception ex) {
            response.setCode(ShoppingRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(ShoppingRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
