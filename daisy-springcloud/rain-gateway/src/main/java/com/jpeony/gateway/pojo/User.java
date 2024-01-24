package com.jpeony.gateway.pojo;

import lombok.Data;

@Data
public class User {
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 登录 token
     */
    private String token;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 城市ID
     */
    private Integer cityId;
}
