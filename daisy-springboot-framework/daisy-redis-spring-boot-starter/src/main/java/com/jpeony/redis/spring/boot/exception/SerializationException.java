package com.jpeony.redis.spring.boot.exception;

/**
 * 序列化异常
 *
 * @author yihonglei
 */
public class SerializationException extends RuntimeException {

    public SerializationException() {
        super();
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
