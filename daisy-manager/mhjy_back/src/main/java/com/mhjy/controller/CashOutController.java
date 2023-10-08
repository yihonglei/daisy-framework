package com.mhjy.controller;

import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.CashOutHistoryBO;
import com.mhjy.pojo.Dto.CashOutDto;
import com.mhjy.pojo.Dto.UIDDto;
import com.mhjy.service.CashOutService;
import com.mhjy.util.ApiResponse;
import com.mhjy.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CashOutController {


    @Autowired
    CashOutService cashOutService;

    /**
     * 发起提现申请
     * @param cashOutDto
     * @return
     */
    @RequestMapping("cashOut")
    public ApiResponse cashOut(@RequestBody CashOutDto cashOutDto) throws Exception {
        try {
            Boolean flag = cashOutService.cashOut(cashOutDto);
            if (flag) {
                return ApiResponse.success("提现申请提交成功");
            } else {
                return ApiResponse.error("提现申请提交失败");
            }
        } catch (BizException e) {
            return ApiResponse.error(e.getErrCode(), e.getErrMessage(), null);
        }catch (Exception e) {
            return ApiResponse.error("提现申请提交失败");
        }
    }

    /**
     * 发起提现申请
     * @param uidDto
     * @return
     */
    @RequestMapping("cashOutHistory")
    public ApiResponse<List<CashOutHistoryBO>> cashOutHistory(@RequestBody UIDDto uidDto) {
            List<CashOutHistoryBO> list = cashOutService.cashOutHistory(uidDto);
            return ApiResponse.success(list);
    }



}
