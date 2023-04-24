package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SysCompanyUserBO;
import com.mhjy.pojo.Bo.SysUserBO;
import com.mhjy.pojo.Dto.*;
import com.mhjy.service.SysCompanyUserService;
import com.mhjy.service.SysUserService;
import com.mhjy.util.ApiResponse;
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

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    /**
     * doLogin - 后台管理系统登录
     * @param sysLoginDto
     */
    @RequestMapping(value = "doLogin")
    public ApiResponse<SysUserBO> doLogin(@RequestBody SysLoginDto sysLoginDto) throws Exception {
        try {
            return ApiResponse.success(sysUserService.doLogin(sysLoginDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * userinfo - 后台管理系统获取用户信息
     * @param headerBO
     */
    @RequestMapping(value = "userinfo")
    public ApiResponse<SysUserBO> userinfo(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        try {
            return ApiResponse.success(sysUserService.userinfo(headerBO.getToken()));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * logout - 后台管理系统用户登出
     * @param headerBO
     */
    @RequestMapping(value = "logout")
    public ApiResponse<Boolean> logout(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) {
        return ApiResponse.success(sysUserService.logout(headerBO));
    }

    /**
     * usersList - 后台管理系统获取用户列表
     * @param headerBO
     */
    @RequestMapping(value = "getSysUsersList")
    public ApiResponse<List<SysUserBO>> getSysUsersList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
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
     * addUser - 后台管理系统-新增用户
     * @param headerBO
     * @param sysUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addSysUser")
    public ApiResponse<SysUserBO> addSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUserDto sysUserDto) throws Exception {
        try {
            return ApiResponse.success(sysUserService.addSysUser(headerBO, sysUserDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * updateSysUser - 后台管理系统-更新用户
     * @param headerBO
     * @param sysUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateSysUser")
    public ApiResponse<SysUserBO> updateSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUserDto sysUserDto) throws Exception {
        try {
            return ApiResponse.success(sysUserService.updateSysUser(headerBO, sysUserDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * addUser - 后台管理系统-删除用户
     * @param headerBO
     * @param sysUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delSysUser")
    public ApiResponse<Boolean> delSysUser(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUserDto sysUserDto) throws Exception {
        try {
            long uid = sysUserDto.getId();
            return ApiResponse.success(sysUserService.delSysUser(uid));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * addUser - 后台管理系统-重置密码
     * @param headerBO
     * @param sysUserDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "resetSysPWD")
    public ApiResponse<Boolean> resetSysPWD(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUserDto sysUserDto) throws Exception {
        try {
            long uid = sysUserDto.getId();
            return ApiResponse.success(sysUserService.resetSysPWD(headerBO, uid));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * addUser - 后台管理系统-修改密码
     * @param headerBO
     * @param sysModifyPWDDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "modifySysPWD")
    public ApiResponse<Boolean> modifySysPWD(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysModifyPWDDto sysModifyPWDDto) throws Exception {
        try {
            return ApiResponse.success(sysUserService.modifySysPWD(headerBO, sysModifyPWDDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * companyUserList - 后台管理系统获取用户列表
     * @param headerBO
     */
    @RequestMapping(value = "companyUserList")
    public ApiResponse<List<SysCompanyUserBO>> companyUserList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysCompanyUserListDto sysCompanyUserListDto) throws Exception {
        try {
            return ApiResponse.success(sysCompanyUserService.sysCompanyUserList(headerBO, sysCompanyUserListDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sysUpdateUserStatus - 修改管理员用户的status
     * @param headerBO
     * @param sysUpdateUserStatusDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateUserStatus")
    public ApiResponse<Boolean> sysUpdateUserStatus(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUpdateUserStatusDto sysUpdateUserStatusDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysUserService.sysUpdateUserStatus(token, sysUpdateUserStatusDto));
    }

    /**
     * sysUpdateUserEnable - 修改普通用户的enable
     * @param headerBO
     * @param sysUpdateEnableDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateUserEnable")
    public ApiResponse<Boolean> sysUpdateUserEnable(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUpdateEnableDto sysUpdateEnableDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysUserService.sysUpdateUserEnable(token, sysUpdateEnableDto));
    }


}
