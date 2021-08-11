package com.jpeony.feign.user.api;

import com.jpeony.feign.user.api.pojo.dto.UserDTO;
import com.jpeony.feign.user.api.pojo.vo.UserVO;
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
    UserVO getUserInfo(@RequestBody UserDTO userDTO);
}
