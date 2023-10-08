package com.jpeony.lotus.api.controller;

import com.jpeony.lotus.common.constant.CommonConstant;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.common.utils.ApiResponse;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysUserVO;
import com.jpeony.lotus.core.pojo.dto.*;
import com.jpeony.lotus.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 后台管理系统登录
     */
    @RequestMapping(value = "doLogin")
    public ApiResponse<SysUserVO> doLogin(@RequestBody SysLoginDTO sysLoginDTO) throws Exception {
        try {
            return ApiResponse.success(sysUserService.doLogin(sysLoginDTO));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统获取用户信息
     */
    @RequestMapping(value = "userinfo")
    public ApiResponse<SysUserVO> userinfo(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        try {
            // TODO 基于缓存 token，转换用户信息，这里为了方便，不增加环境复杂度
            return ApiResponse.success(sysUserService.userInfo(headerBO.getToken()));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统用户登出
     */
    @RequestMapping(value = "logout")
    public ApiResponse<Boolean> logout(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) {
        return ApiResponse.success(sysUserService.logout(headerBO));
    }

    /**
     * 后台管理系统获取用户列表
     */
    @RequestMapping(value = "getSysUsersList")
    public ApiResponse<List<SysUserVO>> getSysUsersList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO)
            throws Exception {
        try {
            return ApiResponse.success(sysUserService.getSysUsersList(headerBO));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统-新增用户
     */
    @RequestMapping(value = "addSysUser")
    public ApiResponse<SysUserVO> addSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                             @RequestBody SysUserDTO sysUserDTO) throws Exception {
        try {
            return ApiResponse.success(sysUserService.addSysUser(headerBO, sysUserDTO));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 后台管理系统-更新用户
     */
    @RequestMapping(value = "updateSysUser")
    public ApiResponse<SysUserVO> updateSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                                @RequestBody SysUserDTO sysUserDTO) throws Exception {
        try {
            return ApiResponse.success(sysUserService.updateSysUser(headerBO, sysUserDTO));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统-删除用户
     */
    @RequestMapping(value = "delSysUser")
    public ApiResponse<Boolean> delSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                           @RequestBody SysUserDTO sysUserDTO) throws Exception {
        try {
            long userId = sysUserDTO.getId();
            return ApiResponse.success(sysUserService.delSysUser(userId));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统-重置密码
     */
    @RequestMapping(value = "resetSysPWD")
    public ApiResponse<Boolean> resetSysPWD(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                            @RequestBody SysUserDTO sysUserDTO) throws Exception {
        try {
            long userId = sysUserDTO.getId();
            return ApiResponse.success(sysUserService.resetSysPWD(headerBO, userId));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台管理系统-修改密码
     */
    @RequestMapping(value = "modifySysPWD")
    public ApiResponse<Boolean> modifySysPWD(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                             @RequestBody SysModifyPWDDTO sysModifyPWDDTO) throws Exception {
        try {
            return ApiResponse.success(sysUserService.modifySysPWD(headerBO, sysModifyPWDDTO));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改管理员用户的 status
     */
    @RequestMapping(value = "sysUpdateUserStatus")
    public ApiResponse<Boolean> sysUpdateUserStatus(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO,
                                                    @RequestBody SysUpdateUserStatusDTO sysUpdateUserStatusDTO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysUserService.sysUpdateUserStatus(token, sysUpdateUserStatusDTO));
    }

}
