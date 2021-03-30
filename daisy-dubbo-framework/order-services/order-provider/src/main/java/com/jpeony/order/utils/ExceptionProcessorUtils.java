package com.jpeony.order.utils;

import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.commons.tool.exception.ExceptionUtil;
import com.jpeony.order.constant.OrderRetCode;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e) {
        try {
            ExceptionUtil.handlerException4biz(response, e);
        } catch (Exception ex) {
            response.setCode(OrderRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(OrderRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
