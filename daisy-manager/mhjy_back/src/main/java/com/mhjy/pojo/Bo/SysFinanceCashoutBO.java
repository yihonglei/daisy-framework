package com.mhjy.pojo.Bo;

import lombok.Data;

@Data
public class SysFinanceCashoutBO {
    // 累计提现金额
    private String outAmount;
    // 提现次数
    private String outCount;
    // 待转账次数
    private String woutCount;

}
