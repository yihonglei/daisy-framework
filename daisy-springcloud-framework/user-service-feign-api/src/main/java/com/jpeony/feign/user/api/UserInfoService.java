package com.jpeony.feign.user.api;

import com.jpeony.feign.user.api.request.UserInfoParam;
import com.jpeony.feign.user.api.response.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-service")
public interface UserInfoService {
    @GetMapping(value = "/user/getUserInfoByUserId")
    String getUserInfoByUserId(@RequestBody int userId);

    @PostMapping(value = "/user/getUserInfo")
    UserInfoDTO getUserInfo(@RequestBody UserInfoParam userInfoParam);
}
