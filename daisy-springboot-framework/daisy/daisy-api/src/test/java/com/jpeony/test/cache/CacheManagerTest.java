package com.jpeony.test.cache;

import com.jpeony.redis.spring.boot.cache.CacheManager;
import com.jpeony.test.BaseServletTest;
import org.junit.Test;

/**
 * 缓存工具测试
 *
 * @author yihonglei
 */
public class CacheManagerTest extends BaseServletTest {
    @Test
    public void testSet() {
        CacheManager.set("key", 1000, "hello world!");

        System.out.println(CacheManager.get("key", String.class));
    }
}
