package com.jpeony.core.service.test;

import com.jpeony.core.pojo.domain.TestDO;
import com.jpeony.core.pojo.dto.TestDTO;
import com.jpeony.core.pojo.vo.TestVO;
import com.jpeony.core.mapper.TestMapper;
import com.jpeony.core.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口实现
 *
 * @author yihonglei
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
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
