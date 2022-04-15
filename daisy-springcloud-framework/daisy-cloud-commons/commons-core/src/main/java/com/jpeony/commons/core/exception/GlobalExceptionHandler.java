package com.jpeony.commons.core.exception;


import com.jpeony.commons.core.enums.ErrorCodeEnum;
import com.jpeony.commons.core.model.ApiRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ApiRes handleBaseException(BaseException e) {
        printWarnLog(e);
        return ApiRes.error(e.getErrCode(), e.getErrMessage(), e.getData());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, TypeMismatchException.class, BindException.class})
    @ResponseBody
    public ApiRes handleParamException(Exception ex) {
        ApiRes result = ApiRes.error();
        // 如果请求参数为必填但没有传则抛异常
        if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            String parameterName = e.getParameterName();
            result.setCode(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(parameterName + " is required");
        } else if (ex instanceof TypeMismatchException) {
            // 参数类型不匹配异常
            TypeMismatchException e = (TypeMismatchException) ex;
            result.setCode(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getMsg());
        } else if (ex instanceof BindException) {
            // 对象参数绑定异常
            BindException e = (BindException) ex;
            result.setCode(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(e.getAllErrors().get(0).getDefaultMessage());
        }

        printWarnLog(ex);
        return result;
    }

    private void printWarnLog(Exception e) {
        log.warn("Warn, ClassName:{}, StackTrace:{}，Message:{}, AllMessage:{}", e.getClass().getName(), getStackTrace(e), e.getMessage(), ExceptionUtils.getStackTrace(e));
    }

    private String getStackTrace(Exception e) {
        StackTraceElement[] stackTraces = e.getStackTrace();
        return Optional.ofNullable(stackTraces).map(st -> st[0] == null ? null : st[0].toString()).orElse(null);
    }

    /**
     * 未知异常
     */
    @ExceptionHandler({RuntimeException.class})
    public ApiRes exceptionHandler(Exception e) {
        return ApiRes.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }
}
