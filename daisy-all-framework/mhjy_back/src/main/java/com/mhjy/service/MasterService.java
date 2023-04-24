package com.mhjy.service;

import com.mhjy.entity.Master;
import com.mhjy.mapper.MasterMapper;
import com.mhjy.entity.Master;
import com.mhjy.entity.User;
import com.mhjy.mapper.MasterMapper;
import com.mhjy.pojo.Bo.MasterBO;
import com.mhjy.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MasterMapper masterMapper;

    @Autowired
    UserService userService;
    /**
     * 新增师徒记录
     * @param master
     * @return
     */
    public Boolean addMaster(Master master) {
        return masterMapper.addMaster(master) == 1;
    }

    public List<Master> originApprenticeList(long master_uid) {
        return masterMapper.apprenticeList(master_uid);
    }

    public User getMyMaster(long uid) {
        Master master = masterMapper.getMyMaster(uid);
        if (master == null) {
            User user = new User();
            user.setNickname("暂无");
            return user;
        }

        long master_uid = master.getMaster_uid();
        User user = userService.getUserInfoWithUid(master_uid);
        if (user == null) {
            user = new User();
            user.setNickname("暂无");
        }

        return user;
    }

    public List<MasterBO> apprenticeList(long master_uid) {
        List<Master> tempList = masterMapper.apprenticeList(master_uid);

        List<MasterBO> result = new ArrayList<>();
        for (Master master:tempList) {
            long apprentice_uid = master.getApprentice_uid();
            User master_user = userService.getUserInfoWithUid(master_uid);
            User apprentice_user = userService.getUserInfoWithUid(apprentice_uid);

            MasterBO masterBO = new MasterBO();
            masterBO.setMaster_uid(master_uid);
            masterBO.setMaster_uname(master_user.getNickname());
            masterBO.setApprentice_uid(apprentice_uid);
            masterBO.setApprentice_uname(apprentice_user.getNickname());
            masterBO.setCreated_at(master.getCreated_at());
            result.add(masterBO);
        }

        return result;
    }

}
