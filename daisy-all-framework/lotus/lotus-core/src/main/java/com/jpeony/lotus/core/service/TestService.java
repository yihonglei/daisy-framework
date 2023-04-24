package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.dto.TestDTO;
import com.jpeony.lotus.core.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
