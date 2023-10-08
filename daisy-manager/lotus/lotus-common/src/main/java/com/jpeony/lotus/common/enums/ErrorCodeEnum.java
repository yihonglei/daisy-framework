package com.jpeony.lotus.common.enums;

import lombok.Getter;

/**
 * 错误码
 *
 * @author yihonglei
 */
public enum ErrorCodeEnum {
    SYSTEM_DEFAULT_ERROR(1, "网络异常,请您重试!"),
    ILLEGAL_ARGUMENT_ERROR(2, "无效参数"),
    DATA_SOURCE_ERROR(3, "多数据源切换异常"),
    /**
     * 权限模块
     */
    SYS_ADD_ROLE_EXIST(0, "角色名称已存在!"),
    SYS_EDIT_ROUTE_NOT_EXIST(0, "修改菜单，待修改的菜单不存在!"),

    /**
     * 用户模块
     */
    SYS_ADD_USER_EXIST(0, "用户名称已存在，请更改用户名!"),
    SYS_EDIT_USER_NOT_EXIST(0, "修改用户信息失败，用户不存在!"),
    SYS_MODIFY_PWD_NOT_RIGHT(0, "修改密码，原始密码不正确!"),
    SYS_MODIFY_PWD_SURE_NOT_RIGHT(0, "修改密码，两次输入密码不一致!"),
    /**
     * 管理系统登录
     */
    SYS_LOGIN_NO_USER(101, "用户不存在，有疑问请联系管理员!"),
    SYS_LOGIN_PWD_ERROR(102, "密码错误，重置请联系管理员!"),
    SYS_LOGIN_USER_NOROLE(103, "用户角色未配置，请联系管理员!"),
    SYS_LOGIN_EXPIRE(201, "登录过期，请重新登录!"),
    LOGIN_ERROR(0, "登录失败!");

    @Getter
    private int code;
    @Getter
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
