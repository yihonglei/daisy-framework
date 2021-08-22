package com.jpeony.user.server.controller;

import com.jpeony.user.api.request.UserInfoParam;
import com.jpeony.user.api.response.UserInfoDTO;
import com.jpeony.user.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoByUserId")
    public String getUser(@RequestParam("userId") int userId) {
        return userInfoService.getUserInfoByUserId(userId);
    }

    @PostMapping(value = "/getUserInfo", produces = "application/json; charset=utf-8")
    public UserInfoDTO getUserInfo(@RequestBody UserInfoParam userInfoParam) {
        return userInfoService.getUserInfo(userInfoParam);
    }
}
