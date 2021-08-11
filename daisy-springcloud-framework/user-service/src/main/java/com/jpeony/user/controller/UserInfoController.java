package com.jpeony.user.controller;

import com.jpeony.feign.user.api.UserInfoService;
import com.jpeony.feign.user.api.pojo.dto.UserDTO;
import com.jpeony.feign.user.api.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoByUserId")
    public String getUser(@RequestBody int userId) {
        return userInfoService.getUserInfoByUserId(userId);
    }

    @PostMapping("/getUserInfo")
    public UserVO getUserInfo(@RequestBody UserDTO userDTO) {
        return userInfoService.getUserInfo(userDTO);
    }
}
