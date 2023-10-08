package com.jpeony.boot.core.service.beanmaps;

import com.jpeony.boot.core.service.MapsBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service("bMapsBeanService")
public class BMapsBeanServiceImpl implements MapsBeanService {

    @Override
    public void sayHello(String name) {
        log.info("sayHelloB={}", name);
    }
}
