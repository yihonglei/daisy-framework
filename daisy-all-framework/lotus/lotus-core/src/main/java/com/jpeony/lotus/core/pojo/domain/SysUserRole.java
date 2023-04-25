package com.jpeony.lotus.core.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRole {
    private long id;
    
    private long userId;

    private long roleId;

    private Date createdAt;

    private Long createdBy;

    private Date updatedAt;

    private Long updatedBy;
}
