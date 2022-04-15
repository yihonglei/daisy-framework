package com.jpeony.boot.core.service.beanmaps;

import com.jpeony.boot.core.service.MapsBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service("aMapsBeanService")
public class AMapsBeanServiceImpl implements MapsBeanService {

    @Override
    public void sayHello(String name) {
        log.info("sayHelloA={}", name);
    }
}
