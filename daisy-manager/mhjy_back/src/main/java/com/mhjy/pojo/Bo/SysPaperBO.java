package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SysPaperBO {

    private long id;
    private long uid;
    private long cid;
    private long sex;
    private String portraits;
    private String vxnum;
    private String intro;
    private long status;
    private long chosen;
    private Date created_at;
    private Date updated_at;

    // sys_user 中的name
    private String companyname;
    // co_user 中的nickname
    private String nickname;


}
