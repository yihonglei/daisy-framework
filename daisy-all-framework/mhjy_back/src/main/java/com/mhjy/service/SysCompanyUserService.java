package com.mhjy.service;

import com.mhjy.entity.Master;
import com.mhjy.entity.SysCompanyUser;
import com.mhjy.entity.SysUser;
import com.mhjy.entity.User;
import com.mhjy.mapper.SysCompanyUserMapper;
import com.mhjy.pojo.Bo.*;
import com.mhjy.pojo.Dto.SysCompanyUserListDto;
import com.mhjy.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysCompanyUserService {

    @Autowired
    SysCompanyUserMapper sysCompanyUserMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    UserService userService;

    public SysCompanyUser userInfoWithUid(long uid) {
        return sysCompanyUserMapper.userInfoWithUid(uid);
    }

    public Boolean addSysCompanyUser(SysCompanyUser sysCompanyUser) {
        return sysCompanyUserMapper.addSysCompanyUser(sysCompanyUser) == 1;
    }

    public List<SysCompanyUserBO> sysCompanyUserList(HeaderBO headerBO, SysCompanyUserListDto sysCompanyUserListDto) throws Exception {
        String token = headerBO.getToken();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        List<Object> roles = sysUserBO.getRoles();
        Boolean containAdmin = false;
        for (Object obj:roles) {
            if (obj instanceof String) {
                String role = (String) obj;
                if (role.equals("admin")) {
                    containAdmin = true;
                    break;
                }
            }
        }

        long cid = sysUserBO.getId();
        String startDate = sysCompanyUserListDto.getStartDate();
        String endDate = sysCompanyUserListDto.getEndDate();
        List<SysCompanyUserBO> result = new ArrayList<>();
        List<SysCompanyUser> tmpList;
        if (containAdmin) {
            tmpList = sysCompanyUserMapper.allSysCompanyUserList(startDate, endDate);
        } else {
            tmpList = sysCompanyUserMapper.sysCompanyUserList(cid, startDate, endDate);
        }

        for (SysCompanyUser sysCompanyUser:tmpList) {
            SysCompanyUserBO sysCompanyUserBO = new SysCompanyUserBO();
            long uid = sysCompanyUser.getUid();
            UserBO userBO = userService.getUserWithUid(uid);
            BeanUtils.copyProperties(userBO, sysCompanyUserBO);

            int invited_by_type = sysCompanyUser.getInvited_by_type();
            long invited_by = sysCompanyUser.getInvited_by();
            if (invited_by_type == 0) { // 管理员用户邀请
                SysUser sysUser = sysUserService.getUserById(invited_by);
                sysCompanyUserBO.setInviteusername(sysUser.getName());
            } else {
                User user = userService.getUserInfoWithUid(invited_by);
                sysCompanyUserBO.setInviteusername(user.getNickname());
            }

            result.add(sysCompanyUserBO);
        }
        return result;
    }

    /**
     * 我的徒弟列表
     * @param master_uid
     * @return
     */
    public List<MasterBO> apprenticeList(long master_uid) {
        List<SysCompanyUser> tempList = sysCompanyUserMapper.apprenticeList(master_uid);

        List<MasterBO> result = new ArrayList<>();
        for (SysCompanyUser sysCompanyUser:tempList) {
            long apprentice_uid = sysCompanyUser.getUid();
            User master_user = userService.getUserInfoWithUid(master_uid);
            User apprentice_user = userService.getUserInfoWithUid(apprentice_uid);

            MasterBO masterBO = new MasterBO();
            masterBO.setMaster_uid(master_uid);
            masterBO.setMaster_uname(master_user.getNickname());
            masterBO.setApprentice_uid(apprentice_uid);
            masterBO.setApprentice_uname(apprentice_user.getNickname());
            masterBO.setCreated_at(sysCompanyUser.getCreated_at());
            result.add(masterBO);
        }

        return result;
    }


    public List<MasterBO> getMyMaster(long uid) {
        List<MasterBO> result = new ArrayList<>();

        SysCompanyUser sysCompanyUser = sysCompanyUserMapper.getMyMaster(uid);
        if (sysCompanyUser != null) {
            MasterBO masterBO = new MasterBO();
            long invited_by = sysCompanyUser.getInvited_by();
            int invited_by_type = sysCompanyUser.getInvited_by_type();
            masterBO.setMaster_uid(invited_by);
            if (invited_by_type == 0) { // 系统管理员邀请 - 组织
                SysUser sysUser = sysUserService.getUserById(invited_by);
                masterBO.setMaster_uname(sysUser.getName());
                masterBO.setType(1);
                masterBO.setCreated_at(sysCompanyUser.getCreated_at());
                result.add(masterBO);
            } else {
                User user = userService.getUserInfoWithUid(uid);
                masterBO.setMaster_uname(user.getNickname());
                masterBO.setType(0);
                masterBO.setCreated_at(sysCompanyUser.getCreated_at());
                result.add(masterBO);

                long cid = sysCompanyUser.getCid();
                MasterBO masterBO1 = new MasterBO();
                masterBO1.setMaster_uid(cid);
                SysUser sysUser = sysUserService.getUserById(cid);
                masterBO1.setMaster_uname(sysUser.getName());
                masterBO1.setType(1);
                masterBO1.setCreated_at(sysCompanyUser.getCreated_at());
                result.add(masterBO1);
            }
        }
        return result;
    }

}
