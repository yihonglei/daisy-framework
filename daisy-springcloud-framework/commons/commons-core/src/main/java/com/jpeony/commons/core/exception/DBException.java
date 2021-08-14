package com.jpeony.commons.core.exception;

import com.jpeony.commons.core.enums.StatusCodeEnum;
import lombok.Data;

/**
 * 数据库异常类
 *
 * @author yihonglei
 */
@Data
public class DBException extends RuntimeException {
    protected int errCode;
    protected String errMessage;
    protected Object data;

    private DBException(int errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public DBException(StatusCodeEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getMsg(), cause);
    }

    public DBException(StatusCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public DBException(String errMessage) {
        this(StatusCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), errMessage, null);
    }

    public DBException(int errCode, String errMessage) {
        this(errCode, errMessage, null);
    }

    public DBException(int errCode, String errMessage, Object data) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.data = data;
    }
}
