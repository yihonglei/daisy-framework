package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysCashOutFee {
    private long id;
    private long uid;
    private String username;
    private int type;
    private String fee;
    private Date created_at;
    private long created_by;
    private Date updated_at;
    private long updated_by;
}
