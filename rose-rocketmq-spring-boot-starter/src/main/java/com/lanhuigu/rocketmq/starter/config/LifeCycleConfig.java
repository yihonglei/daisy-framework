package com.lanhuigu.rocketmq.starter.config;

import com.lanhuigu.rocketmq.starter.lifecycle.LifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * @author yihonglei
 */
@Configuration
public class LifeCycleConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<String, LifeCycle> MAP;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if (context == null || context.getParent() != null) {
            return;
        }
        MAP = context.getBeansOfType(LifeCycle.class, false, false);
        if (MAP == null) {
            return;
        }
        MAP.forEach((k, v) -> v.start());
    }

    @PreDestroy
    public void destroy() {
        if (MAP == null) {
            return;
        }
        MAP.forEach((k, v) -> v.shutdown());
    }
}
