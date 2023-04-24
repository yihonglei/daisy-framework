package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.ImageBO;
import com.mhjy.pojo.Dto.SysAddImageDto;
import com.mhjy.pojo.Dto.SysDeleteImageDto;
import com.mhjy.pojo.Dto.SysUpdateEnableDto;
import com.mhjy.service.ImageService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysImageController {

    @Autowired
    ImageService sysImageService;

    /**
     * sysImageList - 登录时获取用户所有权限菜单
     */
    @RequestMapping(value = "sysImageList")
    public ApiResponse<List<ImageBO>> sysImageList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysImageService.sysImageList());
    }

    /**
     * sysUpdateEnable - 禁用banner
     * @param headerBO
     * @param sysUpdateEnableDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateEnable")
    public ApiResponse<Boolean> sysUpdateEnable(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUpdateEnableDto sysUpdateEnableDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysImageService.sysUpdateEnable(sysUpdateEnableDto));
    }

    /**
     * sysDeleteImage - 禁用banner
     * @param headerBO
     * @param sysDeleteImageDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysDeleteImage")
    public ApiResponse<Boolean> sysDeleteImage(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysDeleteImageDto sysDeleteImageDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysImageService.sysDeleteImage(sysDeleteImageDto.getId()));
    }

    /**
     * sysAddImage - 新建banner
     * @param headerBO
     * @param sysAddImageDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysAddImage")
    public ApiResponse<Boolean> sysAddImage(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysAddImageDto sysAddImageDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysImageService.sysAddImage(sysAddImageDto));
    }

    /**
     * sysUpdateImage - 编辑banner
     * @param headerBO
     * @param sysAddImageDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateImage")
    public ApiResponse<Boolean> sysUpdateImage(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysAddImageDto sysAddImageDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysImageService.sysUpdateImage(sysAddImageDto));
    }






}
