package com.jpeony.pay.biz.payment.context;


import com.jpeony.pay.biz.abs.PaymentContext;

/**
 * 支付宝支付的上下文信息
 */
public class AliPaymentContext extends PaymentContext {
    /**
     * 商品名称（必填）
     */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
