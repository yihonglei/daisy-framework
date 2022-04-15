package com.jpeony.boot.test.service.interceptor;

import com.jpeony.boot.common.spring.interceptor.InterceptorChain;
import com.jpeony.boot.test.BaseServletTest;
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
