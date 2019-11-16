package com.lanhuigu.common.exception;

import com.lanhuigu.common.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * 异常类
 *
 * @author yihonglei
 * @date 2019/11/16 3:14 PM
 */
@Data
public class BizException extends RuntimeException {

    protected int errCode;
    protected String errMessage;
    protected Object data;

    private BizException(int errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BizException(ErrorCodeEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getMsg(), cause);
    }

    public BizException(ErrorCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public BizException(String errMessage) {
        this(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), errMessage, null);
    }

    public BizException(int errCode, String errMessage) {
        this(errCode, errMessage, null);
    }

    public BizException(int errCode, String errMessage, Object data) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.data = data;
    }
}
