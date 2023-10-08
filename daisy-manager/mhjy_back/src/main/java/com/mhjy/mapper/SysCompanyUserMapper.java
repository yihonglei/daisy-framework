package com.mhjy.mapper;

import com.mhjy.entity.SysCompanyUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysCompanyUserMapper {

    List<SysCompanyUser> sysCompanyUserList(long cid, String startDate, String endDate);

    List<SysCompanyUser> allSysCompanyUserList(String startDate, String endDate);

    SysCompanyUser userInfoWithUid(long uid);

    int addSysCompanyUser(SysCompanyUser sysCompanyUser);

    /**
     * 我的徒弟
     * @param uid
     * @return
     */
    List<SysCompanyUser> apprenticeList(long uid);

    /**
     * 我的师傅
     * @param uid
     * @return
     */
    SysCompanyUser getMyMaster(long uid);
}
