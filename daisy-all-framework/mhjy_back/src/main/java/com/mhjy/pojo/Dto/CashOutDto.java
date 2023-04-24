package com.mhjy.pojo.Dto;

import lombok.Data;

@Data
public class CashOutDto {

    private long uid;

    // 提现金额
    private double cashmoney;
    // 支付宝号
    private String alipaynum;
    // 真实姓名
    private String realname;
}
