package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysSet {
    private long id;
    private int type;
    private String value;
    private String desc;
    private Date created_at;
    private long created_by;
    private Date updated_at;
    private long updated_by;
}
