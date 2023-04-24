package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRole {
    private long id;

    private long user_id;

    private long role_id;

    private Date created_at;

    private Long created_by;

    private Date updated_at;

    private Long updated_by;
}
