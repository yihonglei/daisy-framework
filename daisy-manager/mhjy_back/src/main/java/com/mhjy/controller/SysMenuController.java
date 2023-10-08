package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SysMenuBO;
import com.mhjy.pojo.Dto.SysMenuDelDto;
import com.mhjy.pojo.Dto.SysMenuDto;
import com.mhjy.service.SysMenuService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<List<SysMenuBO>> allPermissionRoutes(@RequestAttribute(CommonConstant.HEADER)HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allPermissionRoutes(token));
    }

    /**
     * allRoutes - 后台管理系统用户所有菜单
     */
    @RequestMapping(value = "allRoutes")
    public ApiResponse<List<SysMenuBO>> allRoutes(@RequestAttribute(CommonConstant.HEADER)HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysMenuService.allRoutes(token));
    }

    /**
     * addSysRoute - 后台管理系统用户所有菜单
     * @param headerBO
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addSysRoute")
    public ApiResponse<Boolean> addSysRoute(@RequestAttribute(CommonConstant.HEADER)HeaderBO headerBO, @RequestBody SysMenuDto sysMenuDto) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.addSysRoute(headerBO, sysMenuDto));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * editSysRoute - 后台管理系统用户所有菜单
     * @param headerBO
     * @param sysMenuDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editSysRoute")
    public ApiResponse<Boolean> editSysRoute(@RequestAttribute(CommonConstant.HEADER)HeaderBO headerBO, @RequestBody SysMenuDto sysMenuDto) throws Exception {
        try {
            return ApiResponse.success(sysMenuService.editSysRoute(headerBO, sysMenuDto));
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
    }

    /**
     * delSysRoute - 后台管理系统用户所有菜单
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
