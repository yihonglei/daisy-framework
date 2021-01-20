package com.jpeony.test.service.chain;

import com.jpeony.core.pojo.vo.RiskVO;
import com.jpeony.core.chain.demo.DemoProcessorChainFactory;
import com.jpeony.core.chain.demo.model.DemoContext;
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
        DemoContext context = new DemoContext();
        context.setOrderNo("P999999");

        RiskVO riskVO = DemoProcessorChainFactory.invokeProcess(context);
        System.out.println("riskVO=" + riskVO);
    }
}
