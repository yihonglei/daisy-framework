package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class MasterBO {
    private long master_uid;
    private String master_uname;
    private long apprentice_uid;
    private String apprentice_uname;
    private Date created_at;
    private int type;//0 普通用户邀请 1 组织
}
