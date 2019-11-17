package com.lanhuigu.test.mapper.test;

import com.lanhuigu.common.pojo.domain.TestDO;
import com.lanhuigu.core.mapper.TestMapper;
import com.lanhuigu.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper测试
 *
 * @author yihonglei
 * @date 2019/11/17 8:43 PM
 */
@Slf4j
public class MapperTest extends BaseServletTest {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {
        TestDO testDO = testMapper.queryTestById(1);
        log.info("testDO={}", testDO);
    }
}
