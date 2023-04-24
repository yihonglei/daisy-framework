package com.mhjy.constant;

public class CommonConstant {

    /**
     * header请求
     */
    public final static String HEADER = "header";

    /**
     * user请求
     */
    public final static String USER = "user";

    /**
     * 公众号ID
     */
    public final static String appid = "wxb723f31c61b3b46a";
    /**
     * 公众号secret
     */
    public final static String appSecret = "5f9863337feb0569a9b07bed3024b807";


    /**
     * 微信小程序支付回调地址
     */
    public final static String notify_url = "http://127.0.0.1:8088/sound_channel/payNotify";

    /**
     * 微信商户id
     */
    public final static String mchid = "1623107725";

    /**
     * APIV3密钥
     */
    public final static String apiv3key = "ajsJninq19123hjwq38dDJS332dj2392";

    /**
     * 微信商户证书序列号
     */
    public final static String mchSerialNo = "26B9FE00DCF412B0979F06B4FE22C1C243CAD09C";

    /**
     * 签名方式
     */
    public enum SignType {
        MD5, HMACSHA256
    }

    /**
     * 测试号 APPID
     */
    public final static String csappid = "wx7cad1377e4872efb";
    /**
     * 测试号 secret
     */
    public final static String csappSecret = "c802cb9d75e9c56d038f8ab8a0114bf1";
}
