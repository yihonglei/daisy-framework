package com.lanhuigu.test.service.chain;

import com.lanhuigu.common.pojo.vo.RiskVO;
import com.lanhuigu.core.chain.risk.RiskProcessorChainFactory;
import com.lanhuigu.core.chain.risk.model.RiskContext;
import com.lanhuigu.test.BaseServletTest;
import org.junit.Test;


/**
 * 责任链测试
 *
 * @author yihonglei
 * @date 2019/10/31 6:22 PM
 */
public class ChainTest extends BaseServletTest {

    @Test
    public void testChain() {
        RiskContext context = new RiskContext();
        context.setOrderNo("P999999");

        RiskVO riskVO = RiskProcessorChainFactory.riskProcess(context);
        System.out.println("riskVO=" + riskVO);
    }
}
