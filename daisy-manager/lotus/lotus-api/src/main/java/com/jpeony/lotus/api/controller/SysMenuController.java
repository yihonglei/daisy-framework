package com.jpeony.lotus.api.controller;

import com.jpeony.lotus.common.constant.CommonConstant;
import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.utils.ApiResponse;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysMenuVO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDelDTO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDTO;
import com.jpeony.lotus.core.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    /**
     * 登录时获取用户所有权限菜单
     */
    @RequestMapping(value = "allPermissionRoutes")
    public ApiResponse<List<SysMenuVO>> allPermissionRoutes(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allPermissionRoutes(token));
    }

    /**
     * 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "allRoutes")
    public ApiResponse<List<SysMenuVO>> allRoutes(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allRoutes(token));
    }

    /**
     * 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "addSysRoute")
    public ApiResponse<Boolean> addSysRoute(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysMenuDTO sysMenuDTO) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.addSysRoute(headerBO, sysMenuDTO));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "editSysRoute")
    public ApiResponse<Boolean> editSysRoute(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysMenuDTO sysMenuDTO) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.editSysRoute(headerBO, sysMenuDTO));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "delSysRoute")
    public ApiResponse<Boolean> delSysRoute(@RequestBody SysMenuDelDTO sysMenuDelDTO) {
        try {
            return ApiResponse.success(sysMenuService.delSysRoute(sysMenuDelDTO));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

}
