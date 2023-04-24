package com.mhjy.mapper;

import com.mhjy.entity.Master;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterMapper {
    int addMaster(Master master);

    List<Master> apprenticeList(long master_uid);

    Master getMyMaster(long apprentice_uid);
}
