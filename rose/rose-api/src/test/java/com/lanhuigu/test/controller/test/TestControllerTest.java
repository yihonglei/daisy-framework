package com.lanhuigu.test.controller.test;

import com.lanhuigu.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller测试
 *
 * @author yihonglei
 * @date 2019/11/17 8:54 PM
 */
@Slf4j
public class TestControllerTest extends BaseServletTest {
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        log.info("--------------测试start---------------");
    }

    @After
    public void tearDown() {
        log.info("--------------测试end---------------");
    }

    @Test
    public void cancelPrice() throws Exception {
        mockMvc.perform(get("/test/queryTestById")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.success", is(false)));
    }
}
