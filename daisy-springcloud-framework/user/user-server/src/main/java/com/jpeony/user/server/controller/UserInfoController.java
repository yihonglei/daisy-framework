package com.jpeony.user.server.controller;

import com.jpeony.user.server.api.dto.UserInfoDTO;
import com.jpeony.user.server.api.vo.UserInfoVO;
import com.jpeony.user.server.service.UserInfoService;
import com.jpeony.commons.core.util.R;
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
    public R<UserInfoVO> getUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return R.success(userInfoService.getUserInfo(userInfoDTO));
    }
}
