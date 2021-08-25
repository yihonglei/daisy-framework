package com.jpeony.user.api;

import com.jpeony.user.api.request.UserInfoReq;
import com.jpeony.user.api.response.UserInfoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-server")
public interface UserInfoServerApi {
    @PostMapping(value = "/user/getUserInfo")
    UserInfoRes getUserInfo(@RequestBody UserInfoReq userInfoReq);
}
