package com.jpeony.cloud.user.server.controller;

import com.jpeony.commons.core.model.ResponseDataModel;
import com.jpeony.cloud.user.server.api.dto.UserInfoReq;
import com.jpeony.cloud.user.server.api.vo.UserInfoRes;
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
    public ResponseDataModel<UserInfoRes> getUserInfo(@RequestBody UserInfoReq userInfoReq) {
        return ResponseDataModel.success(userInfoService.getUserInfo(userInfoReq));
    }
}
