package com.jpeony.cloud.user.server.api;

import com.jpeony.cloud.user.server.api.dto.UserInfoDTO;
import com.jpeony.cloud.user.server.api.vo.UserInfoVO;
import com.jpeony.commons.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "user-server")
public interface UserInfoServerApi {
    @PostMapping(value = "/user/getUserInfo")
    R<UserInfoVO> getUserInfo(@RequestBody UserInfoDTO userInfoDTO);
}
