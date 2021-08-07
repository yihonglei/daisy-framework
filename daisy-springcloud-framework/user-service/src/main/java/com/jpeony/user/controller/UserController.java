package com.jpeony.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser/{userId}")
    public String getUser(@PathVariable(value = "userId") int userId) {
        return "hello user, userName = test, userId = " + userId;
    }
}
