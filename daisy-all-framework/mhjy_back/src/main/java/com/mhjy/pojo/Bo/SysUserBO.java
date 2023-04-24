package com.mhjy.pojo.Bo;

import com.mhjy.entity.SysRole;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysUserBO {

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

    private String created_time;

    private String created_by_username;

    private String updated_time;

    private String updated_by_username;

    // 角色信息
    private List<Object> roles;
}
