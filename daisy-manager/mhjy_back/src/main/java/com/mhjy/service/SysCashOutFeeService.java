package com.mhjy.service;

import com.mhjy.entity.SysCashOutFee;
import com.mhjy.entity.SysSet;
import com.mhjy.entity.SysUser;
import com.mhjy.entity.User;
import com.mhjy.mapper.SysCashOutFeeMapper;
import com.mhjy.pojo.Bo.SysCashOutFeeBO;
import com.mhjy.pojo.Dto.SysFeeListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysCashOutFeeService {

    @Autowired
    SysCashOutFeeMapper cashOutFeeMapper;

    @Autowired
    UserService userService;

    @Autowired
    SysUserService sysUserService;

    Boolean addCashOutFee(SysCashOutFee sysCashOutFee) {
        return cashOutFeeMapper.addCashOutFee(sysCashOutFee) == 1;
    }

    SysCashOutFee cashOutFee(int type, long uid) {
        return cashOutFeeMapper.cashOutFee(type, uid);
    }

    public List<SysCashOutFeeBO> sysFeeList(SysFeeListDto sysFeeListDto) {
        int type = sysFeeListDto.getType();
        String username = sysFeeListDto.getUsername();
        List<SysCashOutFee> list = cashOutFeeMapper.sysFeeList(type, username);
        List<SysCashOutFeeBO> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SysCashOutFee sysCashOutFee = list.get(i);
            SysCashOutFeeBO sysCashOutFeeBO = new SysCashOutFeeBO();

            BeanUtils.copyProperties(sysCashOutFee, sysCashOutFeeBO);
            long uid = sysCashOutFee.getUid();
            int type1 = sysCashOutFee.getType();
            if (type1 == 1) {
                  User user = userService.getUserInfoWithUid(uid);
                  sysCashOutFeeBO.setUsername(user.getNickname());
            } else {
                  SysUser sysUser = sysUserService.getUserById(uid);
                  sysCashOutFeeBO.setUsername(sysUser.getUsername());
            }
            result.add(sysCashOutFeeBO);
        }
        return result;
    }

    public Boolean sysUpdateFee(SysCashOutFee sysCashOutFee) {
        long id = sysCashOutFee.getId();
        int type = sysCashOutFee.getType();
        String fee = sysCashOutFee.getFee();
        Boolean flag = cashOutFeeMapper.sysUpdateFee(id, fee) == 1;
        return flag;
    }

    public Boolean sysDeleteFee(SysCashOutFee sysCashOutFee) {
        long id = sysCashOutFee.getId();
        Boolean flag = cashOutFeeMapper.sysDeleteFee(id) == 1;
        return flag;
    }



}
