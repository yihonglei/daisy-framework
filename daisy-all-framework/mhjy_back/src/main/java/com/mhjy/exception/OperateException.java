package com.mhjy.exception;

/**
 * @author yangnan
 */
public class OperateException extends RuntimeException {
    private static final long serialVersionUID = -501068427312161723L;

    public OperateException(String message) {
        super(message);
    }

    public OperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperateException(Throwable cause) {
        super(cause);
    }

    public OperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
