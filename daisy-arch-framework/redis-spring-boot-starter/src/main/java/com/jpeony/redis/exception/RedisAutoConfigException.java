package com.jpeony.redis.exception;

/**
 * @author yihonglei
 */
public class RedisAutoConfigException extends RuntimeException {

    public RedisAutoConfigException() {
    }

    public RedisAutoConfigException(String message) {
        super(message);
    }

    public RedisAutoConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisAutoConfigException(Throwable cause) {
        super(cause);
    }
}
