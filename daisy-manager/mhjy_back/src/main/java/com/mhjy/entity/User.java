package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private long id;

    private String openid;

    private String nickname;

    private String sex;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Date created_at;

    private Date updated_at;
    //extr
//    private long goods_num;
//    private long article_num;
//    private long readed_num;
//    private long fenyou_num;
//    private long zhuwo_num;
//    private long wozhu_num;
//    private long huzhu_num;
}
