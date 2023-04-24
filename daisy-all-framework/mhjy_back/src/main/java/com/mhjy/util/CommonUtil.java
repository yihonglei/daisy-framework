package com.mhjy.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    public static String getBillCode() {
        Random rd = new Random(); // 创建随机对象
        String n = "";            //保存随机数
        int rdGet;                // 取得随机数
        do {
            if (rd.nextInt() % 2 == 1) {
                rdGet = Math.abs(rd.nextInt()) % 10 + 48;  // 产生48到57的随机数(0-9的键位值)
            } else {
                rdGet = Math.abs(rd.nextInt()) % 26 + 97;  // 产生97到122的随机数(a-z的键位值)
            }
            char num1 = (char) rdGet;                      //int转换char
            String dd = Character.toString(num1);
            n += dd;
        } while (n.length() < 8);// 设定长度，此处假定长度小于8
        String r1 = (((Math.random() * 9 + 1) * 100000) + "").substring(0, 6);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String SNDate = sdf.format(new Date());
        String orderCode = r1 + "M" + SNDate + "R" + n.toUpperCase();
        return orderCode;
    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 32位
     * @param source
     * @return
     */
    public static String MD5_32(String source) {
        String md5Result = null;
        try {
            byte[] hash = org.apache.commons.codec.binary.StringUtils.getBytesUtf8(source);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(hash);
            hash = messageDigest.digest();
            md5Result = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Result;
    }

    /**
     * 16位
     * @param source
     * @return
     */
    public static String MD5_16(String source) {
        if(source != null){
            return MD5_32(source).substring(8, 24);
        }else{
            return null;
        }
    }
}
