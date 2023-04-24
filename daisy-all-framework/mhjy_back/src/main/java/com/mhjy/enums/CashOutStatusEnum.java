package com.mhjy.enums;

import lombok.Getter;

public enum CashOutStatusEnum {
    CASH_OUT_STATUS_1(1, "发起提现申请"),
    CASH_OUT_STATUS_2(2, "审核通过"),
    CASH_OUT_STATUS_3(3, "审核拒绝"),
    CASH_OUT_STATUS_Default(100, "默认状态"),
    ;//

    CashOutStatusEnum(int type, String reason) {
        this.status = status;
        this.reason = reason;
    }

    @Getter
    private int status;
    @Getter
    private String reason;
}
