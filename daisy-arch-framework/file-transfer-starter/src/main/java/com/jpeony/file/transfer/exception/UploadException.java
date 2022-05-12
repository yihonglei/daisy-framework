package com.jpeony.file.transfer.exception;

/**
 * 文件上传异常
 *
 * @author : yihonglei
 */
public class UploadException extends RuntimeException {

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
