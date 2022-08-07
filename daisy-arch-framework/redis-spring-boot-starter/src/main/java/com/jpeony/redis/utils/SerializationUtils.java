package com.jpeony.redis.utils;

import com.jpeony.redis.exception.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yihonglei
 */
public class SerializationUtils {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final byte[] EMPTY_ARRAY = new byte[0];

    public static final Map<byte[], byte[]> EMPTY_MAP = new HashMap<>();

    public static boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

    public static byte[] encode(String str) {
        if (str == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }

        try {
            return str.getBytes(DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new SerializationException("Could not write String: " + e.getMessage(), e);
        }
    }
}
