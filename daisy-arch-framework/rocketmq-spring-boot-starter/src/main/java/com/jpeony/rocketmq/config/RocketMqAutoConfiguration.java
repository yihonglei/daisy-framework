package com.jpeony.rocketmq.config;

import com.jpeony.rocketmq.lifecycle.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RocketMqAutoConfiguration implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, LifeCycle> MAP = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent refreshedEvent) {
        ApplicationContext context = refreshedEvent.getApplicationContext();
        if (context.getParent() != null) {
            return;
        }

        Map<String, LifeCycle> map = context.getBeansOfType(LifeCycle.class, false, false);
        map.forEach((k, v) -> {
            v.start();
            logger.info("启动成功完成 key:[{}] value:[{}] 启动状态:[{}]", k, v, v.isStart());
        });
        
        MAP = map;
    }

    @PreDestroy
    public void destroy() {
        if (MAP != null) {
            MAP.forEach((k, v) -> {
                v.shutdown();
                logger.info("销毁成功完成 key:[{}] value:[{}] 启动状态:[{}]", k, v, v.isStart());
            });
        }
    }
}
