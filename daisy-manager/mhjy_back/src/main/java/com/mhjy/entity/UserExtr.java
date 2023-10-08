package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserExtr {
    private long id;

    private long uid;

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
}
