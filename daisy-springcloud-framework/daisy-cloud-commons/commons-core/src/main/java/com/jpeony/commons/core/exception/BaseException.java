package com.jpeony.commons.core.exception;

import com.jpeony.commons.core.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yihonglei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    protected int errCode;
    protected String errMessage;
    protected Object data;


    public BaseException(int errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BaseException(ErrorCodeEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getMsg(), cause);
    }

    public BaseException(ErrorCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public BaseException(String errMessage) {
        this(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), errMessage, null);
    }

    public BaseException(int errCode, String errMessage) {
        this(errCode, errMessage, null);
    }

}
