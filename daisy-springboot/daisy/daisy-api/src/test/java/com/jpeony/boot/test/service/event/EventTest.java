package com.jpeony.boot.test.service.event;

import com.jpeony.boot.common.event.DaisyEvent;
import com.jpeony.boot.common.event.DaisyEventEnum;
import com.jpeony.boot.common.event.DaisyEventManager;
import com.jpeony.boot.test.BaseServletTest;
import org.junit.Test;

/**
 * @author yihonglei
 */
public class EventTest extends BaseServletTest {

    @Test
    public void test() {
        // 事件发布，支持任意参数，可以是一个值，也可以传入对象
        String userName = "Tom";
        DaisyEventManager.fireEvent(new DaisyEvent(DaisyEventEnum.TEST_EVENT, userName));
    }

}
