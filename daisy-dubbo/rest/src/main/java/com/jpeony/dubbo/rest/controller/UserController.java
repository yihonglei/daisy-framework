package com.jpeony.dubbo.rest.controller;

import com.jpeony.dubbo.common.utils.ApiResponse;
import com.jpeony.dubbo.user.client.api.UserService;
import com.jpeony.dubbo.user.client.api.pojo.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("/getUser")
    public ApiResponse getUser(UserDTO userDTO) {
        return userService.getUser(userDTO);
    }
}
