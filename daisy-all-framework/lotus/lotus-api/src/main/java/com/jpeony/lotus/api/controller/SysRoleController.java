package com.jpeony.lotus.api.controller;


import com.jpeony.lotus.common.constant.CommonConstant;
import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.common.utils.ApiResponse;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.bo.SysRoleBO;
import com.jpeony.lotus.core.pojo.dto.SysRoleDto;
import com.jpeony.lotus.core.service.SysRoleService;
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
