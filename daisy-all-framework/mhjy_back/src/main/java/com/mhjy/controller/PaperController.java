package com.mhjy.controller;

import com.mhjy.entity.Paper;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.PaperHistoryBO;
import com.mhjy.pojo.Dto.PaperDto;
import com.mhjy.pojo.Dto.PaperHistoryDto;
import com.mhjy.pojo.Dto.PullPaperDto;
import com.mhjy.service.PaperService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaperController {
    @Autowired
    private PaperService paperService;
    /**
     * 放入纸条
     * @param paperDto
     * @return
     */
    @RequestMapping("pushPaper")
    public ApiResponse<Paper> pushPaper(@RequestBody PaperDto paperDto) throws BizException {
        try {
            Paper paper = paperService.pushPaper(paperDto);
            return ApiResponse.success(paper);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        }
    }

    /**
     * 抽取纸条
     * @param pullPaperDto
     * @return
     */
    @RequestMapping("pullPaper")
    public ApiResponse<Paper> pullPaper(@RequestBody PullPaperDto pullPaperDto){
        Paper paper = paperService.pullPaper(pullPaperDto);
        if (paper == null) {
            return ApiResponse.error(ErrorCodeEnum.PAPER_PULL_NOT_EXIST);
        } else {
            return ApiResponse.success(paper);
        }
    }

    /**
     * 我放的纸条历史/我抽的的纸条历史
     * @param paperHistoryDto
     * @return
     */
    @RequestMapping("paperHistory")
    public ApiResponse<List<PaperHistoryBO>> paperHistory(@RequestBody PaperHistoryDto paperHistoryDto){
        List<PaperHistoryBO> paperList = paperService.paperHistory(paperHistoryDto);
        return ApiResponse.success(paperList);
    }


}
