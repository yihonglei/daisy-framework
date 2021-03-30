package com.jpeony.pay.biz.abs;

import lombok.Data;

/**
 * 退款上下文
 **/
@Data
public class RefundContext extends Context{
	/** 商城退款流水号*/
	private String refundPlatformNo;
	/**用户id**/
	private Long userId;

}