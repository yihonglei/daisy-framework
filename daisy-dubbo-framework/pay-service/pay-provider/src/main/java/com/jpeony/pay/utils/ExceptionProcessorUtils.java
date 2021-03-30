package com.jpeony.pay.utils;

import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.commons.tool.exception.ExceptionUtil;
import com.jpeony.pay.constants.PayReturnCodeEnum;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(PayReturnCodeEnum.SYSTEM_ERROR.getCode());
            response.setMsg(PayReturnCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
