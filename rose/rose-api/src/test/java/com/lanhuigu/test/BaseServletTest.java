package com.lanhuigu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lanhuigu.api.ApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试基类
 *
 * @author yihonglei
 * @date 2019/11/17 1:42 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = ApiApplication.class)
public class BaseServletTest {
    static {
        System.setProperty("spring.profiles.active", "dev");
    }

    @Test
    public void test() {
        System.out.println("dev...");
    }

    protected void print(Object obj) {
        System.out.println(JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect));
    }
}
