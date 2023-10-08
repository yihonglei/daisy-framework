package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SysCompanyUserBO {
    private long id;

    private String openid;

    private String nickname;

    private String sex;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private int enable;

    private int isVIP;

    private Date expire;

    private double income;

    private double remaining;

    private String attCode;

    private String inviteusername;

    private Date created_at;

    private Date updated_at;
}
