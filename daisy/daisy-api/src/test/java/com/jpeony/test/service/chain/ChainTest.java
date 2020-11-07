package com.jpeony.test.service.chain;

import com.jpeony.core.pojo.vo.RiskVO;
import com.jpeony.core.chain.risk.RiskProcessorChainFactory;
import com.jpeony.core.chain.risk.model.RiskContext;
import com.jpeony.test.BaseServletTest;
import org.junit.Test;


/**
 * 责任链测试
 *
 * @author yihonglei
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
