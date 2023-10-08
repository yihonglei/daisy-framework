package com.jpeony.boot.core.service;

import com.jpeony.boot.core.pojo.dto.TestDTO;
import com.jpeony.boot.core.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
