package com.mhjy.service;

import com.mhjy.entity.PaperHistory;
import com.mhjy.mapper.PaperHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperHistoryService {

    @Autowired
    PaperHistoryMapper paperHistoryMapper;

    public Boolean addPaperHistory(PaperHistory paperHistory) {
        return paperHistoryMapper.addPaperHistory(paperHistory) == 1;
    }

    public Boolean delPaperHistory(long id) {
        return paperHistoryMapper.delPaperHistory(id) == 1;
    }

    public List<PaperHistory> paperHistory(long uid,int type) {
        return paperHistoryMapper.paperHistory(uid, type);
    }

    public List<PaperHistory> paperHistoryWithCidTypeSexIntro(long cid, int type, int sex, String intro) {
        return paperHistoryMapper.paperHistoryWithCidTypeSexIntro(cid, type, sex, intro);
    }
}
