package com.jpeony.pay.biz.payment.context;

import com.jpeony.pay.biz.abs.RefundContext;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AliRefundContext extends RefundContext {
    /**
     * 商户订单号（必填）
     */
    private String outTradeNo;
    /**
     * 总金额，单位元（必填）
     */
    private BigDecimal totalFee;
}