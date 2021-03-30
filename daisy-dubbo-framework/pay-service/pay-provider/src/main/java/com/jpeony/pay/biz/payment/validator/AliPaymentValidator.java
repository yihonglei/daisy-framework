package com.jpeony.pay.biz.payment.validator;

import com.jpeony.commons.result.AbstractRequest;
import com.jpeony.order.OrderQueryService;
import com.jpeony.pay.biz.abs.BaseValidator;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service("aliPaymentValidator")
public class AliPaymentValidator extends BaseValidator {
	@Reference(timeout = 3000)
	OrderQueryService orderQueryService;

	@Override
	public void specialValidate(AbstractRequest request) {
       super.commonValidate(request,orderQueryService);
	}
}
