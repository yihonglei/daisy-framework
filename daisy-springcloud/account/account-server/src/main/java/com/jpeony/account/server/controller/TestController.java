package com.jpeony.account.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "SUCCESS";
    }
}
