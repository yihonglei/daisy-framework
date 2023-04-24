package com.mhjy.enums;
import lombok.Getter;

public enum ErrorCodeEnum {
    SUCCESS(1, "success"),
    /**
     * paper 文章
     */
    PAPER_PUSH_ERROR(100, "此微信号已被放入，可联系管理员取回!"),


    /**
     * cashout 提现
     */
    CASH_OUT_ERROR(200, "可提现余额不足!"),



    SYSTEM_DEFAULT_ERROR(0, "数据加载失败,请您稍后重试!"),
    NOT_CANCEL_ERROR(113, "该订单无法取消"),
    ORDER_POOL(212, "订单进入订单池"),
    ORDER_TIME_EXCEPTION(230, "您的航班已落地,您可以马上去约车"),
    BOOKING_DATE_BEFORE_ERROR(230, "预约时间需大于当前系统时间"),
    ILLEGAL_ARGUMENT_ERROR(10000, "无效参数"),
    TOKEN_PARSE_ERROR(10001, "登录已失效"),
    NOT_FOUND_HEADER(10003, "header信息为空"),
    PLACE_ORDER_PARAM_ERROR(10004, "网络异常,请重试"),
    PLACE_ORDER_FAIL(10005, "下单失败"),
    BLACK_USER(239, "下单失败"),
    BOOKING_DATE_ERROR(230, "用车时间有误请检查时间设置"),
    NOT_FOUND_TRIP_COUPON(10007, "行程券不可用"),
    NOT_FOUND_SCENE(10008, "您没有此用车权限"),
    NOT_FOUND_APPLY(10009, "申请不存在"),
    TIME_ERROR(10010, "时间错误"),
    NOT_FOUND_APPROVAL(10011, "审批操作不存在"),
    /**
     * 用户
     */
    NOT_FOUND_CUSTOMER(10002, "未找到用户信息"),

    /**
     * 公号
     */
    OFFICIAL_HEADERIMG_ERROR(0, "发布公号，公号头像不能为空!"),
    OFFICIAL_TITLE_ERROR(0, "发布公号，公号名称不能为空!"),
    OFFICIAL_INTRO_ERROR(0, "发布公号，公号简介不能为空!"),
    OFFICIAL_ORIGINID_ERROR(0, "发布公号，公号原始ID不能为空!"),
    OFFICIAL_NOENOUGH_GOODSNUM_ERROR(0, "发布失败，阅豆数不足!"),
    NOT_FOUND_CURRENT_OFFICIAL(0, "未找到此公号信息，请联系客服处理"),
    OFFICIAL_UNDEL_BEUP_ERROR(0, "请先恢复公号后再上架!"),
    /**
     * 文章
     */
    ARTICLE_TAPNUM_ERROR(0, "发布文章，文章点击次数不能为空!"),
    ARTICLE_TITLE_ERROR(0, "发布文章，文章标题不能为空!"),
    ARTICLE_LINK_ERROR(0, "发布文章，文章链接不能为空!"),
    ARTICLE_NOENOUGH_GOODSNUM_ERROR(0, "发布失败，阅豆数不足!"),
    ARTICLE_UNDEL_BEUP_ERROR(0, "请先恢复文章后再上架!"),
    NOT_FOUND_CURRENT_ARTICLE(0, "未找到此文章信息，请联系客服处理"),


    /**
     * 纸条模块
     */
    PAPER_PULL_NOT_EXIST(0, "客官，暂无可用纸条!"),

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
    LOGIN_ERROR(0, "登录失败!")
    ;
    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private int code;
    @Getter
    private String msg;
}
