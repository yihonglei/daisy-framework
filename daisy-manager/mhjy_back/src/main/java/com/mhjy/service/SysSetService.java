package com.mhjy.service;

import com.mhjy.entity.SysSet;
import com.mhjy.mapper.SysSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSetService {
    @Autowired
    SysSetMapper setMapper;

    public List<SysSet> sysSetWithType(int type) {
        List<SysSet> result = setMapper.sysSet(type);
        return result;
    }

    public List<SysSet> sysSetList() {
        List<SysSet> result = setMapper.sysSetList();
        return result;
    }

    public Boolean sysUpdateSet(SysSet sysSet) {
        long id = sysSet.getId();
        int type = sysSet.getType();
        String value = sysSet.getValue();
        Boolean flag = setMapper.sysUpdateSet(id, value) == 1;
        return flag;
    }
}
