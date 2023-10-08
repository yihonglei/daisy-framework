package com.mhjy.enums;

import lombok.Getter;

public enum RemainTypeEnum {
    Remain_Type_1(1, "徒弟充值阅豆奖励"),
    Remain_Type_2(2, "徒弟超级置顶文章奖励"),
    Remain_Type_3(3, "徒弟超级置顶公号奖励"),
    Remain_Type_10(10, "Ta读了我的文章");

    RemainTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Getter
    private int type;
    @Getter
    private String name;
}
