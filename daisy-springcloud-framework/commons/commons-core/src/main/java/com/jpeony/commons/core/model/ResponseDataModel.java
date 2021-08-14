package com.jpeony.commons.core.model;

import com.jpeony.commons.core.enums.StatusCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yihonglei
 */
public class ResponseDataModel<T> implements Serializable {
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
    private static final ResponseDataModel SUCCESS = new ResponseDataModel(SUCCESS_CODE, "SUCCESS", null);

    /**
     * 失败
     */
    private static final ResponseDataModel ERROR = new ResponseDataModel(ERROR_CODE, "ERROR", null);

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

    private ResponseDataModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static <T> ResponseDataModel<T> success(int code, String msg, T data) {
        return new ResponseDataModel<>(code, msg, data);
    }

    private static <T> ResponseDataModel<T> success(String msg, T data) {
        return success(SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseDataModel<T> success(T data) {
        return success(SUCCESS.getMsg(), data);
    }

    public static <T> ResponseDataModel<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> ResponseDataModel<T> success() {
        return success(SUCCESS.code, SUCCESS.msg, null);
    }

    public static <T> ResponseDataModel error(StatusCodeEnum statusCodeEnum) {
        return error(statusCodeEnum.getCode(), statusCodeEnum.getMsg(), null);
    }

    public static <T> ResponseDataModel error() {
        return error(ERROR.code, ERROR.msg, null);
    }

    public static ResponseDataModel error(int code, String msg, Object data) {
        return new ResponseDataModel<>(code, msg, data);
    }

    private static ResponseDataModel error(String msg, Object data) {
        return error(ERROR.getCode(), msg, data);
    }

    public static ResponseDataModel error(Object data) {
        return error(ERROR.getMsg(), data);
    }

    public static ResponseDataModel error(String msg) {
        return error(msg, null);
    }
}
