package com.jpeony.boot.test.controller;

import com.jpeony.boot.test.BaseServletTest;
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
    }
}
