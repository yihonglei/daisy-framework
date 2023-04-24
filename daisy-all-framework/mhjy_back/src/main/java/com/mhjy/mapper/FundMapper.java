package com.mhjy.mapper;

import com.mhjy.entity.Fund;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundMapper {

    int addFund(Fund fund);

    List<Fund> sysGetFundList(long cid, long uid, int direction);

    List<Fund> fundHistoryWithUidAndDirection(long uid, int direction);
}
