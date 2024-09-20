package com.jpeony.commons.core.exception;

import com.jpeony.commons.core.enums.StatusCodeEnum;
import com.jpeony.commons.core.model.ResponseDataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

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
    public ResponseDataModel handleBaseException(BaseException e) {
        printWarnLog(e);
        return ResponseDataModel.error(e.getErrCode(), e.getErrMessage(), e.getData());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, ConstraintViolationException.class,TypeMismatchException.class, BindException.class})
    @ResponseBody
    public ResponseDataModel handleParamException(Exception ex) {
        ResponseDataModel result = ResponseDataModel.error();
        // 如果请求参数为必填但没有传则抛异常
        if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            String parameterName = e.getParameterName();
            result.setCode(StatusCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(parameterName + " is required");
        } else if (ex instanceof ConstraintViolationException) {
            // 参数规范约束异常
            ConstraintViolationException e = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            Iterator<ConstraintViolation<?>> iterator = violations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<?> next = iterator.next();
                result.setMsg(next.getMessageTemplate());
            }
            result.setCode(StatusCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
        }  else if (ex instanceof TypeMismatchException) {
            // 参数类型不匹配异常
            TypeMismatchException e = (TypeMismatchException) ex;
            result.setCode(StatusCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(StatusCodeEnum.ILLEGAL_ARGUMENT_ERROR.getMsg());
        } else if (ex instanceof BindException) {
            // 对象参数绑定异常
            BindException e = (BindException) ex;
            result.setCode(StatusCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
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
    public ResponseDataModel exceptionHandler(Exception e) {
        return ResponseDataModel.error(StatusCodeEnum.SYSTEM_DEFAULT_ERROR);
    }
}
