package com.jpeony.core.service;

import com.jpeony.core.pojo.dto.TestDTO;
import com.jpeony.core.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
