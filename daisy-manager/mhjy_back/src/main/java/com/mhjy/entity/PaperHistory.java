package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PaperHistory {
    private long id;
    private long uid;
    private long paperid;
    private long cid;
    //1 放入  2 取出
    private long type;
    private Date created_at;
    private Date updated_at;

    // 以下辅助
    private long puid;
    private long sex;
    private String portraits;
    private String vxnum;
    private String intro;
}
