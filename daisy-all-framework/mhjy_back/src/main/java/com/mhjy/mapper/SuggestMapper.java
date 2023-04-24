package com.mhjy.mapper;

import com.mhjy.entity.Suggest;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SuggestMapper {
    int publishSuggest(Suggest suggest);
    List<Suggest> sysMessageList(Date startDate, Date endDate);
}
