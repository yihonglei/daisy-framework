package com.jpeony.cloud.user.server.controller;

import com.jpeony.commons.core.model.ApiRes;
import com.jpeony.cloud.user.server.api.req.UserInfoReq;
import com.jpeony.cloud.user.server.api.res.UserInfoRes;
import com.jpeony.cloud.user.server.service.UserInfoService;
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

    @PostMapping(value = "/getUserInfo", produces = "application/json; charset=utf-8")
    public ApiRes<UserInfoRes> getUserInfo(@RequestBody UserInfoReq userInfoReq) {
        return ApiRes.success(userInfoService.getUserInfo(userInfoReq));
    }
}
