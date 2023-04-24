package com.mhjy.mapper;

import com.mhjy.entity.Paper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaperMapper {
    int pushPaper(Paper paper);

    Paper paperWithId(long paperid);
    // 校验是否有重复的微信号已被放入
    Paper paperWithVxnum(String vxnum);
    //
    Paper paperWithUidAndVxnum(long uid, String vxnum, int status);

    Paper paperWithid(long paperid);

    // 抽公号使用
    List<Paper> pullPaper(int sex);

    List<Paper> chosenList();

    List<Paper> sysAllPushPaperList(int sex, String intro);
    List<Paper> sysPushPaperList(long cid, int sex, String intro);

    int sysUpdatePaperEnable(int status, long id, Date updated_at);

    int updatePaperEnable(int status, long id, Date updated_at);

    int sysUpdatePaperChosen(int chosen, long id, Date updated_at);

    int sysUpdatePaperDel(int del, long id, Date updated_at);

    int sysRealDeletePaper(long id);
}
