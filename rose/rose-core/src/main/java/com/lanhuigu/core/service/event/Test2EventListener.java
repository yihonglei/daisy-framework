package com.lanhuigu.core.service.event;

import com.lanhuigu.common.event.RoseEvent;
import com.lanhuigu.common.event.RoseEventEnum;
import com.lanhuigu.common.event.RoseEventListener;
import com.lanhuigu.common.event.RoseEventManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 实现RoseEventListener接口，在构造方法添加你的事件即可!
 *
 * @author yihonglei
 */
@Slf4j
@Component
public class Test2EventListener implements RoseEventListener {

    public Test2EventListener() {
        RoseEventManager.addListener(RoseEventEnum.TEST_EVENT, this);
    }

    @Override
    public void fire(RoseEvent e) {
        try {
            String userName = (String) e.getSource();
            log.info("【Test2】对发布事件,事件触发,事件参数测试!userName={}", userName);
        } catch (Exception ex) {
            log.error("出异常了!", ex);
        }
    }
}
