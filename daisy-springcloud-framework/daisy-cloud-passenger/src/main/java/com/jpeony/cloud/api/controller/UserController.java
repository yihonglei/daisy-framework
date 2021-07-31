package com.jpeony.cloud.api.controller;

import com.jpeony.feign.api.user.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping("/query")
public class UserController {
    @Autowired
    private UserApi userApi;

    @GetMapping("/user/getUser/{userId}")
    public String helloUser(@PathVariable(value = "userId") int userId) {
        return userApi.getUser(userId);
    }
}
