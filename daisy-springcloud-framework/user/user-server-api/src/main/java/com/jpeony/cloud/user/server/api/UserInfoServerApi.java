package com.jpeony.cloud.user.server.api;

import com.jpeony.cloud.user.server.api.req.UserInfoReq;
import com.jpeony.cloud.user.server.api.res.UserInfoRes;
import com.jpeony.commons.core.model.ResponseDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-server")
public interface UserInfoServerApi {
    @PostMapping(value = "/user/getUserInfo")
    ResponseDataModel<UserInfoRes> getUserInfo(@RequestBody UserInfoReq userInfoReq);
}
