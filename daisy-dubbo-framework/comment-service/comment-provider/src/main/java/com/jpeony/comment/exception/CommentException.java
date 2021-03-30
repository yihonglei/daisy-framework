package com.jpeony.comment.exception;

import com.jpeony.commons.tool.exception.BaseBusinessException;

public class CommentException extends BaseBusinessException {

    public CommentException() {
    }

    public CommentException(String errorCode) {
        super(errorCode);
    }

    public CommentException(String errorCode, String message) {
        super(errorCode, message);
    }
}
