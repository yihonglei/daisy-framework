package com.mhjy.controller;

import com.mhjy.entity.User;
import com.mhjy.entity.UserExtr;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.ShareBO;
import com.mhjy.pojo.Bo.UserBO;
import com.mhjy.pojo.Dto.LoginDto;
import com.mhjy.pojo.Dto.UIDDto;
import com.mhjy.pojo.Dto.URLDto;
import com.mhjy.service.UserService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录接口
 *
 * @description 用户
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * login - 用户微信登录
     *
     * @param loginDto
     */
    @RequestMapping(value = "login")
    public ApiResponse<User> login(@RequestBody LoginDto loginDto) throws Exception {
        try {
            if (loginDto.getCode() == null) {
                return ApiResponse.error("code 为空");
            }
            return ApiResponse.success(userService.login(loginDto.getCode(), loginDto.getAttCode()));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("登录失败，请重新登录");
        }
    }

    /**
     * userinfo - 用户信息
     *
     * @param uidDto
     */
    @RequestMapping(value = "getUserInfo")
    public ApiResponse<UserExtr> getUserInfo(@RequestBody UIDDto uidDto) throws Exception {
        return ApiResponse.success(userService.getUserExtrWithUid(uidDto.getUid()));
    }

    /**
     * userinfo - 用户信息
     *
     * @param uidDto
     */
    @RequestMapping(value = "getOtherUserInfo")
    public ApiResponse<UserBO> getOtherUserInfo(@RequestBody UIDDto uidDto) throws Exception {
        return ApiResponse.success(userService.getUserWithUid(uidDto.getUid()));
    }

    /**
     * userinfo - 用户信息
     *
     * @param urlDto
     */
    @RequestMapping(value = "getwxjsConfig")
    public ApiResponse<ShareBO> getwxjsConfig(@RequestBody URLDto urlDto) throws Exception {
        return ApiResponse.success(userService.getwxjsConfig(urlDto.getUrl()));
    }


}
