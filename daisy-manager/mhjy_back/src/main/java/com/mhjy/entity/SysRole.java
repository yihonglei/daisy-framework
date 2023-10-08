package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private long id;

    private String name;

    private String intro;

    private int sort;

    private Date created_at;

    private long created_by;

    private Date updated_at;

    private long updated_by;
}
