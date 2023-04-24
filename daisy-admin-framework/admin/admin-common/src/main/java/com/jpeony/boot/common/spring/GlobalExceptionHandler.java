package com.jpeony.boot.common.spring;


import com.alibaba.fastjson.JSON;
import com.jpeony.boot.common.exception.BizException;
import com.jpeony.boot.common.enums.ErrorCodeEnum;
import com.jpeony.boot.common.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author yihonglei
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     *
     * @author yihonglei
     */
    @ExceptionHandler(value = {BizException.class})
    @ResponseBody
    public ApiResponse handleBusinessException(BizException e) {
        // 业务异常
        logger.warn("业务处理响应处理失败 errorCode:[{}] errorMessage:[{}] data:[{}]", e.getErrCode(), e.getErrMessage(), JSON
                .toJSONString(e.getData()));
        return ApiResponse.error(e.getErrCode(), e.getErrMessage(), e.getData());
    }

    /**
     * 处理未知的Throwable异常
     *
     * @author yihonglei
     */
    @ExceptionHandler(value = {Throwable.class})
    @ResponseBody
    public ApiResponse handleUnexpectedServerError(HttpServletRequest request, Throwable ex) {
        // 其他未知异常处理
        logger.error("url:[{}] error trace:", request.getRequestURI(), ex);
        return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class, ConstraintViolationException.class,
            TypeMismatchException.class, BindException.class})
    @ResponseBody
    public ApiResponse handleParamException(Throwable ex) {

        ApiResponse result = ApiResponse.error();
        // 如果请求参数为必填但没有传则抛异常
        if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            String parameterName = e.getParameterName();
            result.setCode(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
            result.setMsg(parameterName + " is required");
        } else if (ex instanceof ConstraintViolationException) {
            // 参数规范约束异常
            ConstraintViolationException e = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> next : violations) {
                result.setMsg(next.getMessageTemplate());
            }
            result.setCode(ErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode());
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

        logger.warn("业务处理响应处理失败:[{}]", JSON.toJSONString(result));
        return result;
    }
}
