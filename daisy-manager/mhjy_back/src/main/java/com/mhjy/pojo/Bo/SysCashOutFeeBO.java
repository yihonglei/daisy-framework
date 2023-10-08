package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SysCashOutFeeBO {
    private long id;
    private long uid;
    private int type;
    private String fee;
    private String username;
    private Date created_at;
    private long created_by;
    private Date updated_at;
    private long updated_by;
}
