package com.gpmall.order.utils;

import com.gpmall.commons.result.AbstractResponse;
import com.gpmall.commons.tool.exception.ExceptionUtil;
import com.gpmall.order.constant.OrderRetCode;
import com.gpmall.user.constants.SysRetCodeConstants;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/22-15:48
 */
public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(OrderRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(OrderRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
