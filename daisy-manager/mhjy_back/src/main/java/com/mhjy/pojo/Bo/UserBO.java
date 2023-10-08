package com.mhjy.pojo.Bo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class UserBO {
    private long id;

    private String openid;

    private String nickname;

    private String sex;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private long cid;

    private int ismatch;

    private Date match_expire;

    private int isVIP;

    private Date vip_expire;

    private int enable;

    private double income;

    private double remaining;

    private String attCode;

    private Date created_at;

    private Date updated_at;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
