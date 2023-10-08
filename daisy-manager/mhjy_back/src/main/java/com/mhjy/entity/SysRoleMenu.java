package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenu {
    private long id;

    private long role_id;

    private long menu_id;

    private Date created_at;

    private long created_by;

    private Date updated_at;

    private long updated_by;
}
