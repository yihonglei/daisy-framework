package com.jpeony.pay.biz.abs;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.commons.tool.exception.BizException;
import com.jpeony.pay.dto.PaymentNotifyRequest;

public interface Payment {

    /**
     * 发起交易执行的过程
     * @param request
     * @return
     * @throws BizException
     */
    <T extends AbstractResponse> T process(AbstractRequest request) throws BizException;

    /**
     * 完成交易结果的处理
     * @param request
     * @return
     * @throws BizException
     */
    <T extends AbstractResponse> T completePayment(PaymentNotifyRequest request) throws BizException;
}
