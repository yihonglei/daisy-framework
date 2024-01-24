package com.jpeony.gateway.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yihonglei
 */
public final class R<T> {
    /**
     * 1 成功
     */
    public static final int SUCCESS_CODE = 1;
    /**
     * 0 失败
     */
    public static final int ERROR_CODE = 0;

    /**
     * 成功
     */
    private static final R SUCCESS = new R(SUCCESS_CODE, "SUCCESS", null);
    /**
     * 失败
     */
    private static final R ERROR = new R(ERROR_CODE, "ERROR", null);

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

    public R() {
    }

    public R(int code, String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0;
    }


    public static R error(int code, String msg, Object data) {
        return new R<>(code, msg, data);
    }

    public static R error(String msg, Object data) {
        return error(ERROR.getCode(), msg, data);
    }

    public static R error(Object data) {
        return error(ERROR.getMsg(), data);
    }

    public static R error(String msg) {
        return error(msg, null);
    }

    public static <T> R<T> success(int code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> success(String msg, T data) {
        return success(SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> success(T data) {
        return success(SUCCESS.getMsg(), data);
    }

    public static <T> R<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> R<T> success() {
        return success(SUCCESS.code, SUCCESS.msg, null);
    }

    public static <T> R<T> error() {
        return error(ERROR.code, ERROR.msg, null);
    }
}
