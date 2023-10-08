package com.jpeony.boot.common.exception;

import com.jpeony.boot.common.enums.ErrorCodeEnum;
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

    public DBException(ErrorCodeEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getMsg(), cause);
    }

    public DBException(ErrorCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public DBException(String errMessage) {
        this(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), errMessage, null);
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
