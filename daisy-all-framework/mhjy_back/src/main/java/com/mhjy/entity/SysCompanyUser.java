package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysCompanyUser {

    private long id;

    private long cid;

    private long uid;

    private Date invited_at;

    private long invited_by;

    // 0 系统管理员用户邀请 1 普通用户邀请
    private int invited_by_type;

    private Date created_at;

    private long created_by;

    private Date updated_at;

    private long updated_by;
}
