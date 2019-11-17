package com.lanhuigu.test.service;

import com.lanhuigu.common.spring.interceptor.InterceptorChain;
import com.lanhuigu.test.BaseServletTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 拦截器测试
 *
 * @author yihonglei
 * @date 2019/11/2 10:33 AM
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
