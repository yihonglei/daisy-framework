package com.jpeony.redis.serializer;

import com.jpeony.redis.exception.SerializationException;

/**
 * 对象序列化接口
 *
 * @author yihonglei
 */
public interface Serializer<T> {

    byte[] serialize(T t) throws SerializationException;

    <T> T deserialize(byte[] bytes, Class<T> clz) throws SerializationException;
}
