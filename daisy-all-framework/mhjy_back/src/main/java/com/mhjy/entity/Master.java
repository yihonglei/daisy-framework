package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Master {
    private long master_uid;
    private long apprentice_uid;
    private Date created_at;
    private Date updated_at;
}
