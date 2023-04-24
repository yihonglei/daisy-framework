package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class CashOutHistoryBO {
    private long id;
    //
    private long uid;
    //
    private String username;
    //
    private String cusername;
    //
    private long cid;
    // 提现金额
    private Double cashmoney;
    //
    private int fee;

    // 除去fee的到账金额
    private Double outmoney;
    // 支付宝号
    private String alipaynum;
    // 真实姓名
    private String realname;

    // 状态：1审核中 2审核通过 3 审核拒绝
    private int status;
    // 状态对应的原因
    private String reason;

    private Date created_at;

    private Date updated_at;

    private String created_time;

    private String updated_time;
}
