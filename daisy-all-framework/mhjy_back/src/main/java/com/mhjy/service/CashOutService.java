package com.mhjy.service;

import com.mhjy.entity.*;
import com.mhjy.enums.CashOutStatusEnum;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.CashOutMapper;
import com.mhjy.pojo.Bo.*;
import com.mhjy.pojo.Dto.*;
import com.mhjy.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashOutService {
    @Autowired
    CashOutMapper cashOutMapper;

    @Autowired
    UserService userService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    public Boolean cashOut(CashOutDto cashOutDto) throws BizException {
        long uid = cashOutDto.getUid();
        double cashmoney = cashOutDto.getCashmoney();
        String alipaynum = cashOutDto.getAlipaynum();
        String realname = cashOutDto.getRealname();
        Date now = new Date();

        //校验用户是否有足够的可提现金额
        UserExtr userExtr = userService.getUserExtrWithUid(uid);
        double remaining = userExtr.getRemaining();
        if (remaining < cashmoney) {
            throw new BizException(ErrorCodeEnum.CASH_OUT_ERROR);
        }

        SysCompanyUser sysCompanyUser = sysCompanyUserService.userInfoWithUid(uid);
        long cid = sysCompanyUser.getCid();

        CashOut cashOut = new CashOut();
        cashOut.setUid(uid);
        cashOut.setCid(cid);
        cashOut.setCashmoney(cashmoney);
        cashOut.setAlipaynum(alipaynum);
        cashOut.setRealname(realname);
        cashOut.setStatus(1);
        int fee = 1;//收取1%的手续费
        cashOut.setFee(fee);

        double outmoney = cashmoney*(100-fee)/100;
        cashOut.setOutmoney(outmoney);
        cashOut.setStatus(CashOutStatusEnum.CASH_OUT_STATUS_1.getStatus());
        cashOut.setReason(CashOutStatusEnum.CASH_OUT_STATUS_1.getReason());
        cashOut.setCreated_at(now);
        cashOut.setUpdated_at(now);

        Boolean flag = cashOutMapper.cashout(cashOut) == 1;

        return flag;
    }

    public List<CashOutHistoryBO> cashOutHistory(UIDDto uidDto) {
        List<CashOutHistoryBO> result = new ArrayList<>();
        long uid = uidDto.getUid();
        List<CashOut> list = cashOutMapper.cashOutHistory(uid);
        for (CashOut cashout : list) {
            CashOutHistoryBO cashOutHistoryBO = new CashOutHistoryBO();
            BeanUtils.copyProperties(cashout, cashOutHistoryBO);
            cashOutHistoryBO.setCreated_time(DateUtils.formatString(cashout.getCreated_at(), DateUtils.TIME_DATE));
            cashOutHistoryBO.setUpdated_time(DateUtils.formatString(cashout.getUpdated_at(), DateUtils.TIME_DATE));
            result.add(cashOutHistoryBO);
        }

        return result;
    }

    /**
     * 管理后台
     * @param token
     * @param sysFinanceListDto
     * @return
     */
    public List<CashOutHistoryBO> sysFinanceList(String token, SysFinanceListDto sysFinanceListDto) throws Exception {
        List<CashOutHistoryBO> result = new ArrayList<>();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Boolean containAdmin = false;
        for (Object object:sysUserBO.getRoles()) {
            if (object instanceof String) {
                String rolename = (String) object;
                if (rolename.equals("admin")) {
                    containAdmin = true;
                }
            }
        }
        // 公司ID
        long cid = sysUserBO.getId();
        String alipaynum = sysFinanceListDto.getAlipaynum();
        String realname = sysFinanceListDto.getRealname();
        int status = sysFinanceListDto.getStatus();

        List<CashOut> list;
        if (containAdmin) {
            list = cashOutMapper.sysCashoutList(0, alipaynum, realname, status);
        } else {
            list = cashOutMapper.sysCashoutList(cid, alipaynum, realname, status);
        }

        for (CashOut cashout:list) {
            CashOutHistoryBO cashOutHistoryBO = new CashOutHistoryBO();
            long uid = cashout.getUid();
            long cid1 = cashout.getCid();
            BeanUtils.copyProperties(cashout, cashOutHistoryBO);
            User user = userService.getUserInfoWithUid(uid);
            cashOutHistoryBO.setUsername(user.getNickname());

            SysUser sysUser = sysUserService.getUserById(cid1);
            cashOutHistoryBO.setCusername(sysUser.getName());

            result.add(cashOutHistoryBO);
        }

        return result;
    }

    public Boolean sysVerifyCashOut(HeaderBO headerBO, SysVerifyCashOutDto sysVerifyCashOutDto) {
        long id = sysVerifyCashOutDto.getId();
        int status = sysVerifyCashOutDto.getStatus();
        String reason = sysVerifyCashOutDto.getReason();
        Date now = new Date();
        return cashOutMapper.verifyCashout(id, status, reason, now) == 1;
    }

    public SysFinanceCashoutBO sysFinanceCashoutAny(HeaderBO headerBO, SysFinanceCashoutAnyDto sysFinanceCashoutAnyDto) {
        SysFinanceCashoutBO sysFinanceCashoutBO = new SysFinanceCashoutBO();

        Date date = sysFinanceCashoutAnyDto.getDate();
        int type = sysFinanceCashoutAnyDto.getType();
        String outAmount = cashOutMapper.totalOutMoney(date, type);
        String outCount = cashOutMapper.totalTransCount(date, type, 4);
        String woutCount = cashOutMapper.totalTransCount(date, type, 2);

        if (outAmount == null || outAmount.isEmpty()) {
            outAmount = "0";
        }
        if (outCount == null || outCount.isEmpty()) {
            outCount = "0";
        }
        if (woutCount == null || woutCount.isEmpty()) {
            woutCount = "0";
        }

        sysFinanceCashoutBO.setOutAmount(outAmount);
        sysFinanceCashoutBO.setOutCount(outCount);
        sysFinanceCashoutBO.setWoutCount(woutCount);

        return sysFinanceCashoutBO;
    }

    public Boolean sysVerifyOutMoney(long id) {
        Date now = new Date();
        return cashOutMapper.verifyOutMoney(id, now, 4) == 1;
    }



}
