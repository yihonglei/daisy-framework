package com.jpeony.admin.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RequestMapping("config/test")
@RestController
@Slf4j
public class ConfigTestController {

    @Value("${test.name:ok}")
    private String testName;

    @GetMapping("/test")
    public String test() {
        log.info("testName={}", testName);
        return "SUCCESS";
    }
}
