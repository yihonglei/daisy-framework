package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Paper {

    private long id;
    private long uid;
    private long cid;
    private long sex;
    private String portraits;
    private String vxnum;
    private int money;
    private String intro;
    private long status;
    private long chosen;
    private int del;
    private Date created_at;
    private Date updated_at;

}
