package com.jpeony.boot.test.service.chain;

import com.jpeony.boot.core.pojo.vo.RiskVO;
import com.jpeony.boot.core.chain.demo.DemoProcessorChainFactory;
import com.jpeony.boot.core.chain.demo.model.DemoContext;
import com.jpeony.boot.test.BaseServletTest;
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
