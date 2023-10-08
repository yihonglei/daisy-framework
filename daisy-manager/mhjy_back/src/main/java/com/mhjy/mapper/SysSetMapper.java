package com.mhjy.mapper;

import com.mhjy.entity.SysSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysSetMapper {

    int addSet(SysSet sysSet);

    List<SysSet> sysSet(int type);

    List<SysSet> sysSetList();

    int sysUpdateSet(long id, String value);
}
