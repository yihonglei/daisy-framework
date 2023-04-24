package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.PaperHistoryBO;
import com.mhjy.pojo.Bo.SysPaperBO;
import com.mhjy.pojo.Dto.*;
import com.mhjy.service.PaperService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysPaperController {
    @Autowired
    private PaperService paperService;
    /**
     * 放入纸条列表
     * @param sysPaperListDto
     * @return
     */
    @RequestMapping("pushPaperList")
    public ApiResponse<List<SysPaperBO>> pushPaperList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysPaperListDto sysPaperListDto) throws Exception {
        try {
            String token = headerBO.getToken();
            List<SysPaperBO> result = paperService.sysPushPaperList(token, sysPaperListDto);
            return ApiResponse.success(result);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        }
    }

    /**
     * 抽取纸条列表
     * @param sysPaperListDto
     * @return
     */
    @RequestMapping("pullPaperList")
    public ApiResponse<List<PaperHistoryBO>> pullPaperList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysPaperListDto sysPaperListDto) throws Exception {
        try {
            String token = headerBO.getToken();
            List<PaperHistoryBO> result = paperService.sysPullPaperList(token, sysPaperListDto);
            return ApiResponse.success(result);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        }
    }

    /**
     * sysUpdatePaperEnable - 禁用纸条
     * @param headerBO
     * @param sysUpdatePaperStatusDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdatePaperEnable")
    public ApiResponse<Boolean> sysUpdatePaperEnable(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUpdatePaperStatusDto sysUpdatePaperStatusDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(paperService.sysUpdatePaperStatus(sysUpdatePaperStatusDto));
    }

    /**
     * sysUpdatePaperEnable - 禁用纸条
     * @param headerBO
     * @param sysUpdatePaperChosenDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysUpdatePaperChosen")
    public ApiResponse<Boolean> sysUpdatePaperChosen(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysUpdatePaperChosenDto sysUpdatePaperChosenDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(paperService.sysUpdatePaperChosen(sysUpdatePaperChosenDto));
    }

    /**
     * sysDeletePushPaper - 删除纸条
     * @param headerBO
     * @param sysDeletePaperDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysDeletePushPaper")
    public ApiResponse<Boolean> sysDeletePushPaper(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysDeletePaperDto sysDeletePaperDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(paperService.sysDeletePushPaper(sysDeletePaperDto.getId()));
    }

    /**
     * sysDeletePullPaper - 删除纸条抽取历史
     * @param headerBO
     * @param sysDeletePaperHistoryDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysDeletePullPaper")
    public ApiResponse<Boolean> sysDeletePullPaper(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysDeletePaperHistoryDto sysDeletePaperHistoryDto) throws Exception {
        String token = headerBO.getToken();
        return ApiResponse.success(paperService.sysDeletePushPaper(sysDeletePaperHistoryDto.getId()));
    }

}
