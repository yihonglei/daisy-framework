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
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法工具类
 * <pre>
 *  //待计算散列值的数据
 *  String sourceString = ...;
 *  byte[] sourceBytes = ...;
 *
 *  //计算MD5散列值，返回二进制数组
 *  byte[] resultArray1 = SHAUtils.getMD5Digest(sourceString);
 *  或者
 *  byte[] resultArray2 = SHAUtils.getMD5Digest(sourceBytes);
 *
 *  //计算MD5散列值，返回Hex字符串
 *  String hexString1 = SHAUtils.getMD5DigestHex(sourceString);
 *  或者
 *  String hexString2 = SHAUtils.getMD5DigestHex(sourceBytes);
 *
 *  //计算MD5散列值，返回Base64编码格式字符串
 *  String base64String1 = SHAUtils.getMD5DigestBase64(sourceString);
 *  或者
 *  String base64String2 = SHAUtils.getMD5DigestBase64(sourceBytes);
 * </pre>
 *
 *
 * @author zane
 * @author todd
 * @version 1.2 , 2008/5/7
 * @since JDK1.5
 */
public final class MD5Utils {
	private MD5Utils() {
	}
	private static final String MD5_ALGORITHM = "MD5";

	/**
	 * 返回MD5算法实现
	 *
	 * @return 返回MD5算法实现
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	private static MessageDigest getMD5DigestAlgorithm() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(MD5_ALGORITHM);
	}

	/**
	 * 计算MD5散列值，返回16位<code>byte[]</code>
	 *
	 * @param source
	 * @return 16位<code>byte[]</code>
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static byte[] getMD5Digest(byte[] source) throws NoSuchAlgorithmException {
		return getMD5DigestAlgorithm().digest(source);
	}

	/**
	 * 计算MD5散列值，返回16位<code>byte[]</code>
	 *
	 * @param source
	 * @return 16位<code>byte[]</code>
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static byte[] getMD5Digest(String source) throws NoSuchAlgorithmException {
		return getMD5Digest(source.getBytes());
	}

	/**
	 * 计算MD5散列值，返回32位Hex字符串
	 *
	 * @param source
	 * @return 32位Hex字符串
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static String getMD5DigestHex(byte[] source) throws NoSuchAlgorithmException {
		return new String(Hex.encodeHex(getMD5Digest(source)));
	}

	/**
	 * 计算MD5散列值，返回32位Hex字符串
	 *
	 * @param source
	 * @return 32位Hex字符串
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static String getMD5DigestHex(String source) throws NoSuchAlgorithmException {
		return new String(Hex.encodeHex(getMD5Digest(source)));
	}

	/**
	 * 计算MD5散列值，返回Base64编码格式字符串
	 *
	 * @param source
	 * @return Base64编码格式字符串
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static String getMD5DigestBase64(byte[] source) throws NoSuchAlgorithmException {
		return new String(Base64.encodeBase64(getMD5Digest(source)));
	}

	/**
	 * 计算MD5散列值，返回Base64编码格式字符串
	 *
	 * @param source
	 * @return Base64编码格式字符串
	 * @throws NoSuchAlgorithmException
	 * @since 1.0
	 */
	public static String getMD5DigestBase64(String source) throws NoSuchAlgorithmException {
		return new String(Base64.encodeBase64(getMD5Digest(source)));
	}
}
