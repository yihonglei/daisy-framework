package com.jpeony.lotus.core.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private long id;

    private String username;

    private String password;

    private String salt;

    private String token;

    private String name;

    private String phone;

    private String avatar;

    private String attcode;

    private int status;

    private Date createdAt;

    private long createdBy;

    private Date updatedAt;

    private long updatedBy;

}
