package com.lanhuigu.api.controller;

import com.lanhuigu.common.pojo.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author yihonglei
 * @date 2019/10/12 3:33 PM
 */
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    private TestServiceImpl testService;

    @RequestMapping("/queryTestById")
    public TestVO queryTestById(@RequestParam("id") int id) {
        return testService.queryTestById(id);
    }

    @RequestMapping("queryTestByIdXml")
    public TestVO queryTestByIdXml(@RequestParam("id") int id) {
        return testService.queryTestByIdXml(id);
    }
}
