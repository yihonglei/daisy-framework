package com.jpeony.lotus.api.controller;

import com.jpeony.lotus.common.constant.CommonConstant;
import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.utils.ApiResponse;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.bo.SysMenuBO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDelDto;
import com.jpeony.lotus.core.pojo.dto.SysMenuDto;
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
     * allPermissionRoutes - 登录时获取用户所有权限菜单
     */
    @RequestMapping(value = "allPermissionRoutes")
    public ApiResponse<List<SysMenuBO>> allPermissionRoutes(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allPermissionRoutes(token));
    }

    /**
     * allRoutes - 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "allRoutes")
    public ApiResponse<List<SysMenuBO>> allRoutes(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allRoutes(token));
    }

    /**
     * addSysRoute - 后台管理系统用户所有菜单
     *
     * @param headerBO
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addSysRoute")
    public ApiResponse<Boolean> addSysRoute(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysMenuDto sysMenuDto) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.addSysRoute(headerBO, sysMenuDto));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * editSysRoute - 后台管理系统用户所有菜单
     *
     * @param headerBO
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editSysRoute")
    public ApiResponse<Boolean> editSysRoute(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysMenuDto sysMenuDto) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.editSysRoute(headerBO, sysMenuDto));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * delSysRoute - 后台管理系统用户所有菜单
     *
     * @param sysMenuDelDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delSysRoute")
    public ApiResponse<Boolean> delSysRoute(@RequestBody SysMenuDelDto sysMenuDelDto) {
        try {
            return ApiResponse.success(sysMenuService.delSysRoute(sysMenuDelDto));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }


}
