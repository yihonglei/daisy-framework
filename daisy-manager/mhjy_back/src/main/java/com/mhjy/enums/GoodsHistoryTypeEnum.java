package com.mhjy.enums;

import lombok.Getter;

public enum GoodsHistoryTypeEnum {
    GOODS_HISTORY_TYPE_1(1, "阅豆充值"),
    GOODS_HISTORY_TYPE_2(2, "新用户阅豆奖励"),
    GOODS_HISTORY_TYPE_20(20, "Ta读了我的文章"), // -1
    GOODS_HISTORY_TYPE_21(21, "读了Ta的文章"), // +1
    GOODS_HISTORY_TYPE_22(22, "Ta读了我的文章-点广"), // -5
    GOODS_HISTORY_TYPE_23(23, "读了Ta的文章-点广"), // +5
    GOODS_HISTORY_TYPE_24(24, "Ta读了我的文章-在看"),//+5
    GOODS_HISTORY_TYPE_25(25, "阅读Ta的文章-在看"),//+5
    GOODS_HISTORY_TYPE_26(26, "Ta读了我的文章-无敌置顶"),//-2
    GOODS_HISTORY_TYPE_27(27, "阅读Ta的文章-无敌置顶"),//+2
    GOODS_HISTORY_TYPE_28(28, "Ta读了我的文章-看截图"),//-2
    GOODS_HISTORY_TYPE_29(29, "阅读Ta的文章-看截图"),//+2
    GOODS_HISTORY_TYPE_30(30, "顶文章"),//-10
    GOODS_HISTORY_TYPE_31(31, "顶公号"),//-10
    GOODS_HISTORY_TYPE_40(40, "每日签到"),//+1
    GOODS_HISTORY_TYPE_5(5, "每日分享"),//+1
    GOODS_HISTORY_TYPE_6(6, "互阅一次"),//+1
    GOODS_HISTORY_TYPE_7(7, "邀请新用户奖励"),

    GOODS_HISTORY_TYPE_80(80, "官住他人公号"),//+5
    GOODS_HISTORY_TYPE_81(81, "Ta官住我的公号"),//-6
    GOODS_HISTORY_TYPE_10(10, "今日官住50公号"),//+20
    GOODS_HISTORY_TYPE_11(11, "举报虚假官住"),
    GOODS_HISTORY_TYPE_12(12, "发布文章"),//-10
    GOODS_HISTORY_TYPE_13(13, "发布公号"),//-20
    GOODS_HISTORY_TYPE_15(15, "修改公号"),//-20
    GOODS_HISTORY_TYPE_14(14, "修改文章");//-10

    GoodsHistoryTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Getter
    private int type;
    @Getter
    private String name;
}
