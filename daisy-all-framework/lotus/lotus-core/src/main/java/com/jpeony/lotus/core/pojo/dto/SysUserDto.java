package com.jpeony.lotus.core.pojo.dto;

import lombok.Data;

@Data
public class SysUserDto {
    private long id;

    private long uid;

    // 用户名
    private String username;

    // 姓名
    private String name;

    // 头像
    private String avatar;

    // 手机号
    private String phone;

    // 支付宝号
    private String alipay;

    // 角色 1,2,4,5
    private String roleIds;
}
