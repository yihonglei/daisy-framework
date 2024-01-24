package com.jpeony.gateway.constant;

import lombok.Getter;

public enum ErrorCodeEnum {
    SUCCESS(1, "success"),
    SYSTEM_DEFAULT_ERROR(0, "数据加载失败,请您稍后重试!"),
    TOKEN_PARSE_ERROR(10001, "登录已失效"),
    ;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private int code;
    @Getter
    private String msg;
}
