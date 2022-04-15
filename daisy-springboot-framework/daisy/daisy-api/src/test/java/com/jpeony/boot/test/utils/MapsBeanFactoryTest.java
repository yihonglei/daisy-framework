package com.jpeony.boot.test.utils;

import com.jpeony.boot.core.chain.demo.DemoProcessor;
import com.jpeony.boot.core.service.MapsBeanService;
import com.jpeony.boot.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author yihonglei
 */
@Slf4j
public class MapsBeanFactoryTest extends BaseServletTest {
    /**
     * 使用默认bean id
     */
    @Autowired
    private Map<String, DemoProcessor> maps;
    /**
     * 重命名bean id
     */
    @Autowired
    private Map<String, MapsBeanService> helloMaps;

    @Test
    public void testMaps() {
        DemoProcessor helpCallCarHandlerProcessor = maps.get("helpCallCarHandlerProcessor");
        log.info("helpCallCarHandlerProcessor={}", helpCallCarHandlerProcessor.getClass());
    }

    @Test
    public void testHelloMaps() {
        MapsBeanService aMapsBeanService = helloMaps.get("aMapsBeanService");
        log.info("aMapsBeanService={}", aMapsBeanService.getClass());
        aMapsBeanService.sayHello("AAAA");
    }
}
