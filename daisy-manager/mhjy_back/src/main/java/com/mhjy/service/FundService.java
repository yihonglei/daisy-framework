package com.mhjy.service;

import com.mhjy.entity.Fund;
import com.mhjy.entity.SysUser;
import com.mhjy.entity.User;
import com.mhjy.mapper.FundMapper;
import com.mhjy.pojo.Bo.SysFundBO;
import com.mhjy.pojo.Bo.SysPaperBO;
import com.mhjy.pojo.Bo.SysUserBO;
import com.mhjy.pojo.Dto.SysGetFundListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundService {
    @Autowired
    FundMapper fundMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    UserService userService;

    public Boolean addFund(Fund fund) {
        return fundMapper.addFund(fund) == 1;
    }

    public List<SysFundBO> sysGetFundList(String token, SysGetFundListDto sysGetFundListDto) throws Exception {
        List<SysFundBO> result = new ArrayList<>();
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

        long cid = sysUserBO.getId();
        long uid = sysGetFundListDto.getUid();
        int direction = sysGetFundListDto.getDirection();

        List<Fund> tmpList;
        if (containAdmin) {
            cid = 0;
        }
        tmpList = fundMapper.sysGetFundList(cid, uid, direction);

        for (int i = 0; i < tmpList.size(); i++) {
            Fund fund = tmpList.get(i);
            SysFundBO sysFundBO = new SysFundBO();
            BeanUtils.copyProperties(fund, sysFundBO);
            long tuid = fund.getUid();
            long tcid = fund.getCid();
            User user = userService.getUserInfoWithUid(tuid);
            SysUser sysUser = sysUserService.getUserById(tcid);
            sysFundBO.setNickname(user.getNickname());
            sysFundBO.setCompanyname(sysUser.getName());
            result.add(sysFundBO);
        }

        return result;
    }

    public List<Fund> fundHistory(long uid, int direction) {
        List<Fund> result = fundMapper.fundHistoryWithUidAndDirection(uid, direction);

        return result;
    }

}
