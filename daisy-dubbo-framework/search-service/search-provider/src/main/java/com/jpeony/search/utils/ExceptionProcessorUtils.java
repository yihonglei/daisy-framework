package com.jpeony.search.utils;

import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.commons.tool.exception.ExceptionUtil;
import com.jpeony.search.consts.SearchRetCode;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e) {
        try {
            ExceptionUtil.handlerException4biz(response, e);
        } catch (Exception ex) {
            response.setCode(SearchRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(SearchRetCode.SYSTEM_ERROR.getMsg());
        }
    }
}
