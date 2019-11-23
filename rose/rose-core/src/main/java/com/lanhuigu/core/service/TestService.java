package com.lanhuigu.core.service;

import com.lanhuigu.common.pojo.dto.TestDTO;
import com.lanhuigu.common.pojo.vo.TestVO;

/**
 * 接口列表
 *
 * @author yihonglei
 * @date 2019/11/16 11:21 AM
 */
public interface TestService {
    TestVO queryTestById(TestDTO testDTO);

    TestVO queryTestByIdXml(TestDTO testDTO);
}
