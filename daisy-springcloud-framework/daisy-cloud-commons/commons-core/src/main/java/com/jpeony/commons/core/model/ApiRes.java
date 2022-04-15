package com.jpeony.commons.core.model;

import com.jpeony.commons.core.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yihonglei
 */
public class ApiRes<T> implements Serializable {
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
    private static final ApiRes SUCCESS = new ApiRes(SUCCESS_CODE, "SUCCESS", null);

    /**
     * 失败
     */
    private static final ApiRes ERROR = new ApiRes(ERROR_CODE, "ERROR", null);

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

    private ApiRes(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static <T> ApiRes<T> success(int code, String msg, T data) {
        return new ApiRes<>(code, msg, data);
    }

    private static <T> ApiRes<T> success(String msg, T data) {
        return success(SUCCESS.getCode(), msg, data);
    }

    public static <T> ApiRes<T> success(T data) {
        return success(SUCCESS.getMsg(), data);
    }

    public static <T> ApiRes<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> ApiRes<T> success() {
        return success(SUCCESS.code, SUCCESS.msg, null);
    }

    public static <T> ApiRes error(ErrorCodeEnum errorCodeEnum) {
        return error(errorCodeEnum.getCode(), errorCodeEnum.getMsg(), null);
    }

    public static <T> ApiRes error() {
        return error(ERROR.code, ERROR.msg, null);
    }

    public static ApiRes error(int code, String msg, Object data) {
        return new ApiRes<>(code, msg, data);
    }

    private static ApiRes error(String msg, Object data) {
        return error(ERROR.getCode(), msg, data);
    }

    public static ApiRes error(Object data) {
        return error(ERROR.getMsg(), data);
    }

    public static ApiRes error(String msg) {
        return error(msg, null);
    }
}
