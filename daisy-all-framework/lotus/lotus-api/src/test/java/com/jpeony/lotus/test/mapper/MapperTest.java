package com.jpeony.lotus.test.mapper;

import com.jpeony.lotus.core.mapper.TestMapper;
import com.jpeony.lotus.core.pojo.domain.TestDO;
import com.jpeony.lotus.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper测试
 *
 * @author yihonglei
 */
@Slf4j
public class MapperTest extends BaseServletTest {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void testAnnotation() {
        TestDO testDO = testMapper.queryTestById(1);
        log.info("testDO annotation={}", testDO);
    }

    @Test
    public void testUseMaster() {
        TestDO testDO = testMapper.queryTestByIdMaster(1);
        log.info("testDO useMaster={}", testDO);
    }

    @Test
    public void testUpdate() {
        int i = testMapper.updateTestById(1, "oneone");
        log.info("testUpdate={}", i);
    }

    @Test
    public void testXml() {
        TestDO testDO = testMapper.queryTestByIdXml(1);
        log.info("testDO xml={}", testDO);
    }
}
