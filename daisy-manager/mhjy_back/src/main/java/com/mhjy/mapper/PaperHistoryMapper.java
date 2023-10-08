package com.mhjy.mapper;

import com.mhjy.entity.PaperHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperHistoryMapper {
     int addPaperHistory(PaperHistory paperHistory);

     int delPaperHistory(long id);

     List<PaperHistory> paperHistory(long uid, int type);
     List<PaperHistory> paperHistoryWithCidTypeSexIntro(long cid, int type, int sex, String intro);
}
