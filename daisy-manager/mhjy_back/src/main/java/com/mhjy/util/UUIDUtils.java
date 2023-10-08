package com.mhjy.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class UUIDUtils {
    public static String randomUUID(){
        String uuid = UUID.randomUUID().toString();
        return StringUtils.remove(uuid, "-");
    }

    /**
     * 生成64位UUID
     * @return
     */
    public static String generateUUID64(){
        return (randomUUID()+randomUUID());
    }
}
