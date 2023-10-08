package com.mhjy.pojo.Bo;

import lombok.Data;

@Data
public class SysFinanceChargeAnyBO {
    // 累计充值金额
    private String totalAmount;
    private String vipAmount;
    private String conAmount;
    private String pullAmount;
    private String pushAmount;
}
