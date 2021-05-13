package com.jpeony.cloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${user.name:test}")
    private String userName;

    @GetMapping("/helloUser")
    public String helloUser() {
        return "hello user, user.name = " + userName;
    }
}
