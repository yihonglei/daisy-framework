package com.jpeony.daisy.cloud.admin.controller;

import com.jpeony.daisy.cloud.admin.service.MenuService;
import com.jpeony.daisy.cloud.admin.service.TokenService;
import com.jpeony.daisy.cloud.admin.service.UserService;
import com.jpeony.daisy.cloud.admin.utils.SecuityUtils;
import com.jpeony.daisy.cloud.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/router")
    R router() {
        return R.ok().put("router", menuService.RouterDTOsByUserId(SecuityUtils.getCurrentUser().getId()));
    }


    @RequestMapping("/logout")
    R logout(String token) {
        consumerTokenServices.revokeToken(token);
        return R.ok();
    }


}
