package com.jpeony.boot.core.service.event;

import com.jpeony.boot.common.event.DaisyEvent;
import com.jpeony.boot.common.event.DaisyEventEnum;
import com.jpeony.boot.common.event.DaisyEventListener;
import com.jpeony.boot.common.event.DaisyEventManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 实现daisyEventListener接口，在构造方法添加你的事件即可
 *
 * @author yihonglei
 */
@Slf4j
@Component
public class Test1EventListener implements DaisyEventListener {

    public Test1EventListener() {
        DaisyEventManager.addListener(DaisyEventEnum.TEST_EVENT, this);
    }

    @Override
    public void fire(DaisyEvent e) {
        try {
            String userName = (String) e.getSource();
            log.info("【Test1】对发布事件,事件触发,事件参数测试!userName={}", userName);
        } catch (Exception ex) {
            log.error("出异常了!", ex);
        }
    }
}
