package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.entity.Suggest;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SuggestBO;
import com.mhjy.pojo.Bo.SysMenuBO;
import com.mhjy.pojo.Dto.SysMessageListDto;
import com.mhjy.service.SuggestService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysMessageController {
    @Autowired
    SuggestService suggestService;

    /**
     * sysMessageList - 消息列表
     */
    @RequestMapping(value = "sysMessageList")
    public ApiResponse<List<SuggestBO>> sysMessageList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysMessageListDto sysMessageListDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(suggestService.sysMessageList(sysMessageListDto));
    }
}
