package com.jpeony.core.commons.core.enums;

import lombok.Getter;

/**
 * 错误码
 *
 * @author yihonglei
 */
public enum ErrorCodeEnum {
    SYSTEM_DEFAULT_ERROR(0, "网络异常,请您重试!"),
    ILLEGAL_ARGUMENT_ERROR(10000, "无效参数");

    @Getter
    private int code;
    @Getter
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
