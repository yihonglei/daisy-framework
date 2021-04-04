package com.jpeony.order.biz.handler;

import com.jpeony.commons.tool.exception.BizException;
import com.jpeony.order.biz.context.CreateOrderContext;
import com.jpeony.order.biz.context.TransHandlerContext;
import com.jpeony.order.constant.OrderRetCode;
import com.jpeony.order.dal.persistence.OrderMapper;
import com.jpeony.user.IMemberService;
import com.jpeony.user.dto.QueryMemberRequest;
import com.jpeony.user.dto.QueryMemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateHandler extends AbstractTransHandler {

    @Autowired
    OrderMapper orderMapper;

    @Reference(mock = "com.jpeony.order.biz.mock.MockMemberService", check = false)
    IMemberService memberService;

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        log.info("begin ValidateHandler :context:" + context);
        CreateOrderContext createOrderContext = (CreateOrderContext) context;
        QueryMemberRequest queryMemberRequest = new QueryMemberRequest();
        queryMemberRequest.setUserId(createOrderContext.getUserId());
        QueryMemberResponse response = memberService.queryMemberById(queryMemberRequest);
        if (OrderRetCode.SUCCESS.getCode().equals(response.getCode())) {
            createOrderContext.setBuyerNickName(response.getUsername());
        } else {
            throw new BizException(response.getCode(), response.getMsg());
        }
        return true;
    }
}
