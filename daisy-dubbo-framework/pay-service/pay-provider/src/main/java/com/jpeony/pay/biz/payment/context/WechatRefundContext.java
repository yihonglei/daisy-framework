package com.jpeony.pay.biz.payment.context;

import com.jpeony.pay.biz.abs.RefundContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WechatRefundContext extends RefundContext {

	/** 返回参数 构建退款xml表单 */
	private String xml;
	/**
	 * 订单号
	 */
	private String outTradeNo;

	/**
	 * 微信退款金额
	 */
	private BigDecimal refundFee;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
}