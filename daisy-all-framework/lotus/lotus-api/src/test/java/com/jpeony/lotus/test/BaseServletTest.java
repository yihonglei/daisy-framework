package com.jpeony.lotus.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jpeony.lotus.api.LotusApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试基类
 *
 * @author yihonglei
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = LotusApplication.class)
public class BaseServletTest {
    static {
        System.setProperty("spring.profiles.active", "dev");
    }

    @Test
    public void test() {
        System.out.println("dev...");
    }

    protected void printToJson(Object obj) {
        System.out.println(JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect));
    }
}
