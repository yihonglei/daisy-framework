package com.jpeony.redis.spring.boot.serializer;

import com.jpeony.redis.spring.boot.exception.SerializationException;

/**
 * 对象序列化接口
 *
 * @author yihonglei
 */
public interface Serializer<T> {

    byte[] serialize(T t) throws SerializationException;

    <T> T deserialize(byte[] bytes, Class<T> clz) throws SerializationException;
}
