package com.mhjy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhjy.constant.CommonConstant;
import com.mhjy.entity.Master;
import com.mhjy.entity.SysCompanyUser;
import com.mhjy.entity.User;
import com.mhjy.entity.UserExtr;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.UserExtrMapper;
import com.mhjy.mapper.UserMapper;
import com.mhjy.pojo.Bo.ShareBO;
import com.mhjy.pojo.Bo.UserBO;
import com.mhjy.util.HttpClientUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserExtrMapper userExtrMapper;

    @Autowired
    MasterService masterService;

    @Autowired
    SysSetService sysSetService;

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    public UserBO getUserWithUid(long uid) {
        User user = userMapper.getUserInfoWithUid(uid);
        UserExtr userExtr = userExtrMapper.getUserExtr(uid);
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(user, userBO);
        userBO.setIsVIP(userExtr.getIsVIP());
        userBO.setVip_expire(userExtr.getVip_expire());
        userBO.setEnable(userExtr.getEnable());
        userBO.setIsmatch(userExtr.getIsmatch());
        userBO.setMatch_expire(userExtr.getMatch_expire());
        userBO.setIncome(userExtr.getIncome());
        userBO.setRemaining(userExtr.getRemaining());
        userBO.setAttCode(userExtr.getAttCode());
        userBO.setCid(userExtr.getCid());
        return userBO;
    }

    public UserBO getUserWithAttCode(String attCode) {
        UserExtr userExtr = userExtrMapper.getUserExtrWithAttCode(attCode);
        if (userExtr != null) {
            long uid = userExtr.getUid();
            return getUserWithUid(uid);
        }
        return null;
    }

    public User getUserInfoWithUid(long uid) {
        return userMapper.getUserInfoWithUid(uid);
    }



    public UserExtr getUserExtrWithUid(long uid) {
        checkUserExtr(uid);
        return userExtrMapper.getUserExtr(uid);
    }

    public User login(String code, String attCode) throws Exception {
        String userinfo = getOauthAccessToken(code);
        JSONObject userJson = JSONObject.parseObject(userinfo);
        User user = JSON.toJavaObject(userJson, User.class);
        if (user == null) {
            throw new BizException(ErrorCodeEnum.LOGIN_ERROR);
        }
        user.setNickname(new String(user.getNickname().getBytes("ISO-8859-1"), "UTF-8"));

        User adUser = userMapper.getUserWithOpenId(user.getOpenid());
        if (adUser == null) {
            user.setCreated_at(new Date());
            user.setUpdated_at(new Date());
            Boolean flag = userMapper.addUser(user) == 1;

            logger.info("=======开始查找attCode对应的用户信息=======");
            UserBO userBO = getUserWithAttCode(attCode);
            int invited_by_type = 0;
            if (userBO != null) {
                logger.info("=======查找attCode对应的用户信息不为空，结果=======", userBO.toString());
                invited_by_type = 1;
            } else {
                logger.info("=======attCode对应的用户信息为空=======");
                userBO = getUserWithUid(1); // 超级管理员
                logger.info("=======超级管理员的attCode对应的信息，结果=======", userBO.toString());
            }
            if (flag) {
                User user1 = userMapper.getUserWithOpenId(user.getOpenid());

                logger.info("=======开始更新sys_company_user信息=======");
                SysCompanyUser sysCompanyUser = new SysCompanyUser();
                sysCompanyUser.setCid(userBO.getCid());
                sysCompanyUser.setUid(user1.getId());
                sysCompanyUser.setInvited_at(new Date());
                sysCompanyUser.setInvited_by(userBO.getId());
                sysCompanyUser.setInvited_by_type(invited_by_type);
                sysCompanyUser.setCreated_at(new Date());
                sysCompanyUser.setCreated_by(1);
                sysCompanyUser.setUpdated_at(new Date());
                sysCompanyUser.setUpdated_by(1);
                Boolean flag2 = sysCompanyUserService.addSysCompanyUser(sysCompanyUser);
                logger.info("=======更新sys_company_user信息结果：=======", flag2);

                logger.info("=======开始生成userExtr信息=======");

                UserExtr userExtr = new UserExtr();
                userExtr.setUid(user1.getId());
                userExtr.setEnable(1);
                userExtr.setCid(userBO.getCid());
                String attCode1 = RandomStringUtils.random(8, true, false).toUpperCase();
                userExtr.setAttCode(attCode1);
                userExtr.setCreated_at(new Date());
                userExtr.setUpdated_at(new Date());
                Boolean flag1 = userExtrMapper.addUserExtr(userExtr) == 1;
                if (flag1) {
                    logger.info("=======userExtr信息新增成功=======");
                } else {
                    logger.error("=======userExtr信息新增失败=======");
                }

                return user1;
            } else {
                throw new BizException(ErrorCodeEnum.LOGIN_ERROR);
            }
        } else {
            return adUser;
        }
    }

    /**
     * 根据code 获取授权的token 仅限授权时使用，与全局的access_token不同
     * @param code
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String getOauthAccessToken(String code) throws Exception {
        String data = null;//redisService.get("WEIXIN_SQ_ACCESS_TOKEN");
        String rs_access_token = null;
        String rs_openid = null;
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+
                CommonConstant.appid+
                "&secret="+CommonConstant.appSecret+
                "&code="+code+
                "&grant_type=authorization_code";
//        if (StringUtils.isEmpty(data)) {
//            synchronized (this) {
//                //已过期，需要刷新
//                String hs = HttpClientUtil.doGet(url);
//                JSONObject json = JSONObject.parseObject(hs);
//                String refresh_token = json.getString("refresh_token");
//
//                String refresh_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
//                        +CommonConstant.csappid+
//                        "&grant_type=refresh_token&refresh_token="+refresh_token;
//                String r_hs = HttpClientUtil.doGet(refresh_url);
//                JSONObject r_json = JSONObject.parseObject(r_hs);
//                String r_access_token = r_json.getString("access_token");
//                String r_expires_in = r_json.getString("expires_in");
//                rs_openid = r_json.getString("openid");
//
//                rs_access_token = r_access_token;
////                redisService.set("WEIXIN_SQ_ACCESS_TOKEN", r_access_token, Integer.parseInt(r_expires_in) - 3600);
//                logger.info("Set sq access_token to redis is successful.parameters time:{},realtime",Integer.parseInt(r_expires_in), Integer.parseInt(r_expires_in) - 3600);
//            }
//        }else{
            //还没有过期
            String hs = HttpClientUtil.doGet(url);
            JSONObject json = JSONObject.parseObject(hs);
            rs_access_token = json.getString("access_token");
            rs_openid = json.getString("openid");
//        }

        return getOauthUserInfo(rs_access_token, rs_openid);
    }

    /**
     * 根据授权token获取用户信息
     * @param access_token
     * @param openid
     * @return
     */
    public String getOauthUserInfo(String access_token, String openid) throws Exception{
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN";
        try {
            String hs = HttpClientUtil.doGet(url);
            logger.info("userinfo=", hs);
            return hs;
        } catch (IOException e) {
            logger.error("RestFul of authorization is error.",e);
        }
        return null;
    }

    /**
     * ===================用户额外信息===================
     */
    //更新用户Cid
    public Boolean updateUserExtrCid(long cid, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrCid(cid, uid, new Date()) == 1;
    }
    //更新用户可用状态
    public Boolean updateUserEnable(int enable, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrEnable(enable, uid, new Date()) == 1;
    }

    //更新VIP
    public Boolean updateUserExtrVIP(int isVIP, Date vip_expire, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrVIP(isVIP, uid, vip_expire, new Date()) == 1;
    }

    //更新续费信息
    public Boolean updateUserExtrMatch(int ismatch, Date match_expire, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrMatch(ismatch, uid, match_expire, new Date()) == 1;
    }

    //更新续费信息
    public Boolean updateUserExtrIncome(double income, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrIncome(income, uid, new Date()) == 1;
    }

    //更新续费信息
    public Boolean updateUserExtrRemain(double remain, long uid) {
        checkUserExtr(uid);
        return userExtrMapper.updateUserExtrRemain(remain, uid, new Date()) == 1;
    }
    /**
     * 获取用户的可提现余额
     * @param uid
     * @return int 阅豆数
     */
    public double getUserExtrRemaining(long uid) {
        UserExtr userExtr = userExtrMapper.getUserExtr(uid);
        if (userExtr != null) {
            double remaining = userExtr.getRemaining();
            return remaining;
        }

        return 0.0;
    }

    private void checkUserExtr(long uid) {
        UserExtr userExtr = userExtrMapper.getUserExtr(uid);
        if (userExtr == null) {
            Date now = new Date();
            UserExtr userExtr1 = new UserExtr();
            String attCode = RandomStringUtils.random(8, true, false).toUpperCase();
            logger.info("=========={}=====", attCode);
            userExtr1.setAttCode(attCode);
            userExtr1.setUid(uid);
            userExtr1.setCreated_at(now);
            userExtr1.setUpdated_at(now);
            userExtrMapper.addUserExtr(userExtr1);
        }
    }

    public ShareBO getwxjsConfig(String url) throws Exception {
        url = URLDecoder.decode(url,"UTF-8");
        String access_token = getShareAccessToken();

        String jsapi_ticket = getJsapi_ticket(access_token);

        // 注意 URL 一定要动态获取，不能 hardcode
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        ShareBO shareBO = new ShareBO();
        shareBO.setUrl(ret.get("url"));
        shareBO.setJsapi_ticket(ret.get("jsapi_ticket"));
        shareBO.setNonce_str(ret.get("nonceStr"));
        shareBO.setTimestamp(ret.get("timestamp"));
        shareBO.setSignature(ret.get("signature"));
        shareBO.setAppId(CommonConstant.appid);

        return shareBO;
    }

    /**
     * access_token
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String getShareAccessToken() throws Exception {
        try {
            String rs_access_token = null;
            String expires_in = null;
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
                    CommonConstant.appid+
                    "&secret="+CommonConstant.appSecret;
            //还没有过期
            String hs = HttpClientUtil.doGet(url);
            JSONObject json = JSONObject.parseObject(hs);
            rs_access_token = json.getString("access_token");
            expires_in = json.getString("expires_in");
            return rs_access_token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * access_token
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String getJsapi_ticket(String access_token) throws Exception {
        try {
            String ticket = null;
            String expires_in = null;
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+
                    access_token+"&type=jsapi";
            //还没有过期
            String hs = HttpClientUtil.doGet(url);
            JSONObject json = JSONObject.parseObject(hs);
            String errcode = json.getString("errcode");
            if (errcode.equalsIgnoreCase("0")) {
                ticket = json.getString("ticket");
                expires_in = json.getString("expires_in");
            } else {
                ticket = "error";
            }

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
