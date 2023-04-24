package com.mhjy.pojo.Dto;

import lombok.Data;

@Data
public class PaperDto {

    /**
     * uid
     */
    private long uid;
    /**
     * 形象照
     */
    private String portraits;

    /**
     * 微信号
     */
    private String vxnum;

    /**
     * 费用
     */
    private int money;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 性别
     */
    private int sex;


}
