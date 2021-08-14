package com.jpeony.commons.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;

/**
 * 解码器
 *
 * @author yihonglei
 */
public final class DecodeUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(DecodeUtils.class);

    /**
     * 解码
     */
    public static String decode(String str, int length) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        String r = decode(str);
        if (r.length() > length) {
            return r.substring(0, length);
        }
        return r;
    }

    /**
     * 解码
     */
    public static String decode(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            LOGGER.error("解码异常", e);
        }
        return str;
    }
}
