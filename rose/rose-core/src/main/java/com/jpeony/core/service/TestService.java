package com.jpeony.core.service;

import com.jpeony.common.pojo.dto.TestDTO;
import com.jpeony.common.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
