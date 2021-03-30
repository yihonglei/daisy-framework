package com.jpeony.user.utils;

import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.commons.tool.exception.ExceptionUtil;
import com.jpeony.user.constants.SysRetCodeConstants;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(SysRetCodeConstants.SYSTEM_ERROR.getCode());
            response.setMsg(SysRetCodeConstants.SYSTEM_ERROR.getMessage());
        }
    }
}
