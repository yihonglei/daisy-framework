package com.mhjy.controller;

import com.mhjy.service.MasterService;
import com.mhjy.entity.User;
import com.mhjy.pojo.Bo.MasterBO;
import com.mhjy.pojo.Dto.UIDDto;
import com.mhjy.service.MasterService;
import com.mhjy.service.SysCompanyUserService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MasterController {
    @Autowired
    MasterService masterService;

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    /**
     * 徒弟列表
     * @param uidDto
     * @return
     */
    @RequestMapping("apprenticeList")
    public ApiResponse<List<MasterBO>> apprenticeList(@RequestBody UIDDto uidDto) {
        List<MasterBO> apprenticeList = sysCompanyUserService.apprenticeList(uidDto.getUid());
        return ApiResponse.success(apprenticeList);
    }

    /**
     * 我的师傅
     * @param uidDto
     * @return
     */
    @RequestMapping("getMyMaster")
    public ApiResponse<List<MasterBO>> getMyMaster(@RequestBody UIDDto uidDto) {
        List<MasterBO> list = sysCompanyUserService.getMyMaster(uidDto.getUid());
        return ApiResponse.success(list);
    }


}
