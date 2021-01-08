package com.jpeony.test.service.interceptor;

import com.jpeony.common.spring.interceptor.InterceptorChain;
import com.jpeony.test.BaseServletTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 拦截器测试
 *
 * @author yihonglei
 */
public class InterceptorTest extends BaseServletTest {
    @Autowired
    private InterceptorChain interceptorChain;

    @Test
    public void testChain() {
        boolean b = interceptorChain.preHandle("jjjj");
        System.out.println("b:" + b);
    }
}
