package com.jpeony.commons.datasource.exception;

import lombok.Getter;

/**
 * 错误码
 *
 * @author yihonglei
 */
public enum ErrorCodeEnum {
    SYSTEM_DEFAULT_ERROR(0, "网络异常,请您重试!"),
    DATA_SOURCE_ERROR(20000, "多数据源切换异常");

    @Getter
    private int code;
    @Getter
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
