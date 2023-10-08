package com.jpeony.lotus.core.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysUserVO {

    private Long id;

    private Long uid;

    private String username;

//    private String password;

//    private String salt;

    private String token;

    private String name;

    private String avatar;

    private String phone;

    private String alipay;

    private String attcode;

    private double income;

    private double remaining;

    private int status;

    private String createdTime;

    private String createdByUsername;

    private String updatedTime;

    private String updatedByUsername;

    // 角色信息
    private List<Object> roles;
    
}
