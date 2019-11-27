package com.lanhuigu.core.service;

import com.lanhuigu.common.pojo.dto.TestDTO;
import com.lanhuigu.common.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
