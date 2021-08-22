package com.jpeony.user.api;

import com.jpeony.user.api.request.UserInfoParam;
import com.jpeony.user.api.response.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-server")
public interface UserServerApi {
    @GetMapping(value = "/user/getUserInfoByUserId")
    String getUserInfoByUserId(@RequestBody int userId);

    @PostMapping(value = "/user/getUserInfo")
    UserInfoDTO getUserInfo(@RequestBody UserInfoParam userInfoParam);
}
