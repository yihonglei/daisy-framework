package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.entity.SysCashOutFee;
import com.mhjy.entity.SysSet;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SysCashOutFeeBO;
import com.mhjy.pojo.Dto.SysFeeListDto;
import com.mhjy.pojo.Dto.SysUpdatePaperStatusDto;
import com.mhjy.service.SysCashOutFeeService;
import com.mhjy.service.SysSetService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysSetController {
    @Autowired
    SysSetService sysSetService;

    @Autowired
    SysCashOutFeeService sysCashOutFeeService;

    /**
     * sysSetList - 设置列表
     */
    @RequestMapping(value = "sysSetList")
    public ApiResponse<List<SysSet>> sysSetList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysSetService.sysSetList());
    }

    /**
     * sysUpdateSet - 更新系统设置的值
     * @param headerBO
     * @param sysSet
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateSet")
    public ApiResponse<Boolean> sysUpdateSet(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysSet sysSet) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysSetService.sysUpdateSet(sysSet));
    }

    /**
     * sysFeeList - 设置列表
     */
    @RequestMapping(value = "sysFeeList")
    public ApiResponse<List<SysCashOutFeeBO>> sysFeeList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysFeeListDto sysFeeListDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysCashOutFeeService.sysFeeList(sysFeeListDto));
    }

    /**
     * sysUpdateFee - 更新系统设置fee
     * @param headerBO
     * @param sysCashOutFee
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdateFee")
    public ApiResponse<Boolean> sysUpdateFee(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysCashOutFee sysCashOutFee) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysCashOutFeeService.sysUpdateFee(sysCashOutFee));
    }

    /**
     * sysDeleteFee - 删除系统设置fee
     * @param headerBO
     * @param sysCashOutFee
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysDeleteFee")
    public ApiResponse<Boolean> sysDeleteFee(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysCashOutFee sysCashOutFee) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(sysCashOutFeeService.sysDeleteFee(sysCashOutFee));
    }



}
