/*
 *
 * Copyright (c) 2006- CE, Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * CE Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CE.
 */
package com.jpeony.gateway.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static com.jpeony.gateway.constant.CommonConstant.DES_ENCRYPT_KEY1;
import static com.jpeony.gateway.constant.CommonConstant.DES_ENCRYPT_KEY2;


/**
 * DES算法工具类
 * <pre>
 *    //DES 加密解密算法以
 *    String sourceString = ...;
 *    String key = ...;
 *
 *    //计算SHA散列值，返回二进制数组
 *    String result1 = DESUtils.encrypt(sourceString,key);
 *
 *    String result2 = SHAUtils.getSHADigest(result1,key);
 *
 * 	  result2  == sourceString;
 * </pre>
 *
 * @author will
 * @author todd
 * @since JDK1.5
 */
public final class DESUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(DESUtils.class);

    /**
     * 不创建实例
     */
    private DESUtils() {
    }

    private static final String KEY_ALGORITHM = "DES";

    /**
     * 生成加密key
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec des = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(des);
        return secretKey;
    }

    /**
     * base64 网络传输规则特殊字符
     *
     * @param secStr
     * @return
     */
    public static String enreplace(String secStr) {
        secStr = secStr.replaceAll("\\+", "\\-");
        secStr = secStr.replaceAll("\\/", "\\*");
        secStr = secStr.replaceAll("\\=", "\\.");
        return secStr;
    }

    /**
     * base64 网络传输规则特殊字符
     *
     * @param secStr
     * @return
     */
    public static String dereplace(String secStr) {
        secStr = secStr.replaceAll("\\-", "\\+");
        secStr = secStr.replaceAll("\\*", "\\/");
        secStr = secStr.replaceAll("\\.", "\\=");
        return secStr;
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     */
    public static String encrypt(String data, String key) {
        String keyDes = key;
        keyDes = StringUtils.center(keyDes, 8, "*");
        try {
            return enreplace(new String(Base64.encodeBase64(encrypt(data.getBytes(), keyDes.getBytes()))));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k, random);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    public static String decrypt(String data, String key) {
        String keyDes = key;
        keyDes = StringUtils.center(keyDes, 8, "*");
        data = dereplace(data);
        try {
            return new String(decrypt(Base64.decodeBase64(data.getBytes()), keyDes.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k, random);
        return cipher.doFinal(data);
    }

    /**
     * DES加密	Mode ECB,padding PKCS7
     *
     * @param value
     * @param key
     * @return 十六进制加密串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String DesEncryptToHex(String value, String key) {

        return toHexString(DesEnctypt(value.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.US_ASCII)))
                .toUpperCase();
    }


    public static byte[] DesEnctypt(byte[] plainText, byte[] rawKey) {

        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(rawKey);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 正式执行加密操作
            byte[] encryptedData = cipher.doFinal(plainText);

            return encryptedData;
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }


    /**
     * DES加密两次
     *
     * @param str
     * @return
     */
    public static String doubleEncrypt(String str) {
        try {
            return encrypt(encrypt(str, DES_ENCRYPT_KEY1), DES_ENCRYPT_KEY2);
        } catch (Exception e) {
            LOGGER.error("des双加密异常", e);
        }
        return null;
    }

    /**
     * DES解密
     *
     * @param str
     * @return
     */
    public static String doubleDecrypt(String str) {
        try {
            return decrypt(decrypt(str, DES_ENCRYPT_KEY2), DES_ENCRYPT_KEY1);
        } catch (Exception e) {
            LOGGER.error("des双解密异常", e);
        }
        return null;
    }
}
