package com.mhjy.controller;

import com.mhjy.pojo.Dto.SuggestDto;
import com.mhjy.service.SuggestService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SuggestController {

    @Autowired
    SuggestService suggestService;
    /**
     * 发布建议
     * @param suggestDto
     * @return
     */
    @RequestMapping("publishSuggest")
    public ApiResponse publishSuggest(@RequestBody SuggestDto suggestDto){
        Boolean flag = suggestService.publishSuggest(suggestDto);
        if (flag) {
            return ApiResponse.success(flag);
        } else {
            return ApiResponse.error(flag);
        }
    }
}
