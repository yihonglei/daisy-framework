package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private long id;

    private long uid;

    private String username;

    private String password;

    private String salt;

    private String token;

    private String name;

    private String phone;

    private String avatar;

    private String attcode;

    private int status;

    private Date created_at;

    private long created_by;

    private Date updated_at;

    private long updated_by;

}
