package com.mhjy.controller;

import com.mhjy.constant.CommonConstant;
import com.mhjy.pojo.Bo.*;
import com.mhjy.pojo.Dto.*;
import com.mhjy.service.CashOutService;
import com.mhjy.service.FundService;
import com.mhjy.service.TradeService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/api")
public class SysFinanceController {

    @Autowired
    CashOutService cashOutService;

    @Autowired
    TradeService tradeService;

    @Autowired
    FundService fundService;
    /**
     * 提现申请列表
     * @param sysFinanceListDto
     * @return
     */
    @RequestMapping("sysFinanceList")
    public ApiResponse<List<CashOutHistoryBO>> sysFinanceList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysFinanceListDto sysFinanceListDto) throws Exception {
        List<CashOutHistoryBO> list = cashOutService.sysFinanceList(headerBO.getToken(), sysFinanceListDto);
        return ApiResponse.success(list);
    }

    /**
     * 提现申请列表
     * @param sysVerifyCashOutDto
     * @return
     */
    @RequestMapping("sysVerifyCashOut")
    public ApiResponse<Boolean> sysVerifyCashOut(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysVerifyCashOutDto sysVerifyCashOutDto) throws Exception {
        Boolean flag = cashOutService.sysVerifyCashOut(headerBO, sysVerifyCashOutDto);
        return ApiResponse.success(flag);
    }

    /**
     * 提现统计
     * @param headerBO
     * @param sysFinanceCashoutAnyDto
     * @return
     * @throws Exception
     */
    @RequestMapping("sysFinanceCashoutAny")
    public ApiResponse<SysFinanceCashoutBO> sysFinanceCashoutAny(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysFinanceCashoutAnyDto sysFinanceCashoutAnyDto) throws Exception {
        SysFinanceCashoutBO sysFinanceCashoutBO = cashOutService.sysFinanceCashoutAny(headerBO, sysFinanceCashoutAnyDto);
        return ApiResponse.success(sysFinanceCashoutBO);
    }

    /**
     *
     * @param headerBO
     * @param sysVerifyOutMoneyDto
     * @return
     * @throws Exception
     */
    @RequestMapping("sysVerifyOutMoney")
    public ApiResponse<Boolean> sysVerifyOutMoney(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysVerifyOutMoneyDto sysVerifyOutMoneyDto) throws Exception {
        Boolean flag = cashOutService.sysVerifyOutMoney(sysVerifyOutMoneyDto.getId());
        return ApiResponse.success(flag);
    }

    /**
     * 充值记录列表
     * @param sysChargeListDto
     * @return
     */
    @RequestMapping("sysChargeList")
    public ApiResponse<List<SysTradeBO>> sysChargeList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysChargeListDto sysChargeListDto) throws Exception {
        List<SysTradeBO> list = tradeService.sysChargeList(headerBO.getToken(), sysChargeListDto);
        return ApiResponse.success(list);
    }


    @RequestMapping("sysFinanceChargeAny")
    public ApiResponse<SysFinanceChargeAnyBO> sysFinanceChargeAny(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysFinanceChargeAnyDto sysFinanceChargeAnyDto) throws Exception {
        SysFinanceChargeAnyBO sysFinanceChargeAnyBO = tradeService.sysFinanceChargeAny(headerBO, sysFinanceChargeAnyDto);
        return ApiResponse.success(sysFinanceChargeAnyBO);
    }

    /**
     * 资金进出记录列表
     * @param sysGetFundListDto
     * @return
     */
    @RequestMapping("sysGetFundList")
    public ApiResponse<List<SysFundBO>> sysGetFundList(@RequestAttribute(CommonConstant.HEADER) HeaderBO headerBO, @RequestBody SysGetFundListDto sysGetFundListDto) throws Exception {
        List<SysFundBO> list = fundService.sysGetFundList(headerBO.getToken(), sysGetFundListDto);
        return ApiResponse.success(list);
    }


}
