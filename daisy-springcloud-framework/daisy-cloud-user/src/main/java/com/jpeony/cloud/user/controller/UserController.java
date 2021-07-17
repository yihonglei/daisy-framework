package com.jpeony.cloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
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
    public String helloUser(@PathVariable int userId) {
        return "hello user, userName = test, userId = " + userId;
    }
}
