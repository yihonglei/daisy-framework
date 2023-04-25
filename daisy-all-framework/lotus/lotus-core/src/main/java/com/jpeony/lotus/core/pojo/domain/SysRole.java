package com.jpeony.lotus.core.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private long id;

    private String name;

    private String intro;

    private int sort;

    private Date createdAt;

    private long createdBy;

    private Date updatedAt;

    private long updatedBy;
}
