package com.jpeony.common.util;

import com.jpeony.common.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应规范
 *
 * @author yihonglei
 */
public final class DaisyResponse<T> implements Serializable {
    /**
     * 1 成功
     */
    private static final int SUCCESS_CODE = 1;

    /**
     * 0 失败
     */
    private static final int ERROR_CODE = 0;

    /**
     * 成功
     */
    private static final DaisyResponse SUCCESS = new DaisyResponse(SUCCESS_CODE, "SUCCESS", null);

    /**
     * 失败
     */
    private static final DaisyResponse ERROR = new DaisyResponse(ERROR_CODE, "ERROR", null);

    /**
     * 返回状态码
     */
    @Getter
    @Setter
    private int code;

    /**
     * 提示信息
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 数据
     */
    @Getter
    @Setter
    private T data;

    private DaisyResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static <T> DaisyResponse<T> success(int code, String msg, T data) {
        return new DaisyResponse<>(code, msg, data);
    }

    private static <T> DaisyResponse<T> success(String msg, T data) {
        return success(SUCCESS.getCode(), msg, data);
    }

    public static <T> DaisyResponse<T> success(T data) {
        return success(SUCCESS.getMsg(), data);
    }

    public static <T> DaisyResponse<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> DaisyResponse<T> success() {
        return success(SUCCESS.code, SUCCESS.msg, null);
    }

    public static <T> DaisyResponse error(ErrorCodeEnum errorCodeEnum) {
        return error(errorCodeEnum.getCode(), errorCodeEnum.getMsg(), null);
    }

    public static <T> DaisyResponse error() {
        return error(ERROR.code, ERROR.msg, null);
    }

    public static DaisyResponse error(int code, String msg, Object data) {
        return new DaisyResponse<>(code, msg, data);
    }

    private static DaisyResponse error(String msg, Object data) {
        return error(ERROR.getCode(), msg, data);
    }

    public static DaisyResponse error(Object data) {
        return error(ERROR.getMsg(), data);
    }

    public static DaisyResponse error(String msg) {
        return error(msg, null);
    }

}
