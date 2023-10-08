package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SysRoleBO;
import com.mhjy.pojo.Dto.SysRoleDto;
import com.mhjy.service.SysRoleService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    /**
     * allRoles - 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "allRoles")
    public ApiResponse<List<SysRoleBO>> allRoles(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysRoleService.allRoles());
    }

    /**
     * addRole - 后台管理系统 新增角色
     */
    @RequestMapping(value = "addRole")
    public ApiResponse<SysRoleBO> addRole(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysRoleDto sysRoleDto) throws Exception {
        try {
            String token = headerBO.getToken();
            return ApiResponse.success(sysRoleService.addRole(token, sysRoleDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }

    /**
     * addRole - 后台管理系统 编辑角色
     */
    @RequestMapping(value = "editRole")
    public ApiResponse<SysRoleBO> editRole(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysRoleDto sysRoleDto) throws Exception {
        try {
            String token = headerBO.getToken();
            return ApiResponse.success(sysRoleService.editRole(token, sysRoleDto));
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }


    /**
     * addRole - 后台管理系统 删除角色
     */
    @RequestMapping(value = "delRole")
    public ApiResponse<Boolean> delRole(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysRoleDto sysRoleDto) throws Exception {
        try {
            String token = headerBO.getToken();
            return ApiResponse.success(sysRoleService.delRole(token, sysRoleDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }


}
