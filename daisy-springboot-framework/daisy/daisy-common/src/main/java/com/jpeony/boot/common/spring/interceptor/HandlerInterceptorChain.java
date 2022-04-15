package com.jpeony.boot.common.spring.interceptor;

import com.jpeony.boot.common.annotation.Intercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 拦截器链
 *
 * @author yihonglei
 */
@Component
public class HandlerInterceptorChain implements SmartInterceptorChain {
    @Autowired
    private List<HandlerInterceptor> interceptors;

    @Override
    public void sort() {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }

        interceptors.sort((interceptor1, interceptor2) -> {
            Intercept c1 = interceptor1.getClass().getAnnotation(Intercept.class);
            Intercept c2 = interceptor2.getClass().getAnnotation(Intercept.class);
            return Integer.compare(c1.order(), c2.order());
        });
    }

    @Override
    public boolean preHandle(String context) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return true;
        }

        // order从小到大开始执行
        this.sort();

        for (HandlerInterceptor interceptor : interceptors) {
            boolean preHandle = interceptor.preHandle(context);
            if (!preHandle) {
                return false;
            }
        }

        return true;
    }

}
