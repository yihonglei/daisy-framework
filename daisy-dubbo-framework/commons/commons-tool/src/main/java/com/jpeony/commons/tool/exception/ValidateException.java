package com.jpeony.commons.tool.exception;

public class ValidateException extends BaseBusinessException {

    public ValidateException() {
        super();
    }

    public ValidateException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ValidateException(Throwable arg0) {
        super(arg0);
    }

    public ValidateException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ValidateException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ValidateException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
