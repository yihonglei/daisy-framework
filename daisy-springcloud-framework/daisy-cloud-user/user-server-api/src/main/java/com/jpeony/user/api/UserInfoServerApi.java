package com.jpeony.user.api;

import com.jpeony.commons.core.model.ApiRes;
import com.jpeony.user.api.req.UserInfoReq;
import com.jpeony.user.api.res.UserInfoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-server")
public interface UserInfoServerApi {
    @PostMapping(value = "/user/getUserInfo")
    ApiRes<UserInfoRes> getUserInfo(@RequestBody UserInfoReq userInfoReq);
}
