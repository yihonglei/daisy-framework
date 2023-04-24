package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class PaperHistoryBO {
    private long id;
    private long uid;
    private long paperid;

    private long cid;

    //1 放入  2 取出
    private long type;

    private long sex;
    private String portraits;
    private String vxnum;
    private int money;
    private String intro;
    private long status;
    private long chosen;

    private String created_time;
    private String updated_time;

    private Date created_at;
    // 以下辅助
    private long puid;
    private String companyname;
    // 抽取人昵称
    private String cnickname;
    // 被抽取人昵称
    private String pnickname;
}
