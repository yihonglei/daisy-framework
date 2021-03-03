package com.jpeony.redis.serializer.impl;

import com.jpeony.redis.exception.SerializationException;
import com.jpeony.redis.serializer.Serializer;
import com.jpeony.redis.utils.SerializationUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

/**
 * JDK 序列化方式
 *
 * @author yihonglei
 */
public class JdkSerializer implements Serializer<Object> {
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    public JdkSerializer() {
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return SerializationUtils.EMPTY_ARRAY;
        } else {
            try {
                return this.serializer.convert(object);
            } catch (Exception var3) {
                throw new SerializationException("Cannot serialize", var3);
            }
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws SerializationException {
        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        } else {
            try {
                return (T) this.deserializer.convert(bytes);
            } catch (Exception var3) {
                throw new SerializationException("Cannot deserialize", var3);
            }
        }
    }
}
