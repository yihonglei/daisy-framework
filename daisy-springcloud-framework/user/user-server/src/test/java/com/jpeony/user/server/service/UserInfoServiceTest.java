package com.jpeony.user.server.service;

import com.jpeony.user.server.BaseServletTest;
import com.jpeony.user.server.api.dto.UserInfoDTO;
import com.jpeony.user.server.api.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yihonglei
 */
@Slf4j
public class UserInfoServiceTest extends BaseServletTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testGetUserInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(1);
        userInfoDTO.setAge(18);

        UserInfoVO userInfoVO = userInfoService.getUserInfo(userInfoDTO);
        log.info("userInfoVO = {}", userInfoVO);
    }

}
