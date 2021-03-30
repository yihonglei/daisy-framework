package com.jpeony.pay.services;

import com.alibaba.fastjson.JSON;
import com.jpeony.pay.biz.abs.BasePayment;
import com.jpeony.pay.utils.ExceptionProcessorUtils;
import com.jpeony.pay.PayCoreService;
import com.jpeony.pay.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * @author 风骚的Mic 老师
 * create-date: 2019/7/30-13:54
 */
@Slf4j
@Service(cluster = "failover")
public class PayCoreServiceImpl implements PayCoreService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentResponse execPay(PaymentRequest request) {

        PaymentResponse paymentResponse=new PaymentResponse();
        try {
            paymentResponse=BasePayment.paymentMap.get(request.getPayChannel()).process(request);
        }catch (Exception e){
            log.error("PayCoreServiceImpl.execPay Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(paymentResponse,e);
        }
        return paymentResponse;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentNotifyResponse paymentResultNotify(PaymentNotifyRequest request) {
        log.info("paymentResultNotify request:{}", JSON.toJSONString(request));
        PaymentNotifyResponse response=new PaymentNotifyResponse();
        try{
            response=BasePayment.paymentMap.get
                    (request.getPayChannel()).completePayment(request);

        }catch (Exception e){
            log.error("paymentResultNotify occur exception:"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }finally {
            log.info("paymentResultNotify return result:{}",JSON.toJSONString(response));
        }
        return response;
    }

    /**
     * 执行退款
     * @param refundRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RefundResponse execRefund(RefundRequest refundRequest) {
        RefundResponse refundResponse=new RefundResponse();
        try {
            refundResponse=BasePayment.paymentMap.get(refundRequest.getPayChannel()).process(refundRequest);
        }catch (Exception e){
            log.error("PayCoreServiceImpl.execRefund Occur Exception :{}",e);
            ExceptionProcessorUtils.wrapperHandlerException(refundResponse,e);
        }
        return refundResponse;
    }
}
