package com.jpeony.commons.core.enums;

import lombok.Getter;

/**
 * @author yihonglei
 */
public enum StatusCodeEnum {

    /**
     * 状态码枚举类
     */
    SUCCESS(1, "success"),
    SYSTEM_DEFAULT_ERROR(0, "网络异常,请您重试!"),
    ILLEGAL_ARGUMENT_ERROR(10000, "无效参数"),
    DATA_SOURCE_ERROR(20000, "多数据源切换异常");


    @Getter
    private int code;
    @Getter
    private String msg;

    StatusCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        StatusCodeEnum[] enums = StatusCodeEnum.values();
        for (StatusCodeEnum enu : enums) {
            if (enu.getCode() == code) {
                return enu.getMsg();
            }
        }
        return "";
    }

}
