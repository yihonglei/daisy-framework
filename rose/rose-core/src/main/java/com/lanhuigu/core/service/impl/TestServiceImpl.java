package com.lanhuigu.core.service.impl;

import com.lanhuigu.common.pojo.domain.TestDO;
import com.lanhuigu.common.pojo.dto.TestDTO;
import com.lanhuigu.common.pojo.vo.TestVO;
import com.lanhuigu.core.mapper.TestMapper;
import com.lanhuigu.core.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口实现
 *
 * @author yihonglei
 * @date 2019/11/16 11:12 AM
 */
@Slf4j
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public TestVO queryTestById(TestDTO testDTO) {
        TestDO testDO = testMapper.queryTestById(testDTO.getId());

        TestVO vo = new TestVO();
        BeanUtils.copyProperties(testDO, vo);

        return vo;
    }

    @Override
    public TestVO queryTestByIdXml(TestDTO testDTO) {
        TestDO testDO = testMapper.queryTestByIdXml(testDTO.getId());

        TestVO vo = new TestVO();
        BeanUtils.copyProperties(testDO, vo);

        return vo;
    }
}
