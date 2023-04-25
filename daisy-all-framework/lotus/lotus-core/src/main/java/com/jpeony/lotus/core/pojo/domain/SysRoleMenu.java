package com.jpeony.lotus.core.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenu {
    private long id;

    private long roleId;

    private long menuId;

    private Date createdAt;

    private long createdBy;

    private Date updatedAt;

    private long updatedBy;
}
