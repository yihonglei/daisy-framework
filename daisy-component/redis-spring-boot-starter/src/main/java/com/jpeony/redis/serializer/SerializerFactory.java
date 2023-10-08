package com.jpeony.redis.serializer;

import com.jpeony.redis.serializer.impl.JdkSerializer;
import com.jpeony.redis.exception.SerializationException;
import com.jpeony.redis.serializer.impl.Jackson2JsonSerializer;
import org.springframework.util.StringUtils;

/**
 * 序列化器工厂
 *
 * @author yihonglei
 */
public class SerializerFactory {

    private static final String JACKSON_SERIALIZER = "jackson";

    private static final String JDK_SERIALIZER = "jdk";

    public static Serializer create(String serializer) {
        if (StringUtils.isEmpty(serializer) || JACKSON_SERIALIZER.equalsIgnoreCase(serializer)) {
            return new Jackson2JsonSerializer();
        } else if (JDK_SERIALIZER.equalsIgnoreCase(serializer)) {
            return new JdkSerializer();
        } else {
            return createByClassName(serializer);
        }
    }

    /**
     * 根据全类名反射得到序列化器
     *
     * @param serializerClassName
     * @return
     */
    private static Serializer createByClassName(String serializerClassName) {
        try {
            Class<?> clazz;
            try {
                clazz = Class.forName(serializerClassName, true,
                        Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                clazz = Class.forName(serializerClassName);
            }
            Object instance = clazz.newInstance();
            if (instance instanceof Serializer<?>) {
                @SuppressWarnings("unchecked")
                Serializer<?> serializer = (Serializer<?>) instance;
                return serializer;
            } else {
                throw new SerializationException("Unable to create Serializer instance of type " +
                        serializerClassName + ", Because it does not implement Serializer interface.");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new SerializationException("Unable to create Serializer instance of type " + serializerClassName, e);
        }
    }
}
