package com.mhjy.controller;

import com.mhjy.entity.Fund;
import com.mhjy.pojo.Dto.FundHistoryDto;
import com.mhjy.pojo.Dto.GoodsListDto;
import com.mhjy.service.FundService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FundController {

    @Autowired
    FundService fundService;

    /**
     * 收益记录列表
     * @param fundHistoryDto
     * @return
     */
    @RequestMapping("fundHistory")
    public ApiResponse<List<Fund>> fundHistory(@RequestBody FundHistoryDto fundHistoryDto){
        List<Fund> list = fundService.fundHistory(fundHistoryDto.getUid(), fundHistoryDto.getDirection());
        return ApiResponse.success(list);
    }

}
