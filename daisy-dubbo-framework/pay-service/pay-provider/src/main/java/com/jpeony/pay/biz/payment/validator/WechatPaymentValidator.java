package com.jpeony.pay.biz.payment.validator;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.order.OrderQueryService;
import com.jpeony.pay.biz.abs.BaseValidator;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service("wechatPaymentValidator")
public class WechatPaymentValidator extends BaseValidator {
    @Reference(timeout = 3000)
    OrderQueryService orderQueryService;

    @Override
    public void specialValidate(AbstractRequest request) {
        commonValidate(request, orderQueryService);
    }
}
