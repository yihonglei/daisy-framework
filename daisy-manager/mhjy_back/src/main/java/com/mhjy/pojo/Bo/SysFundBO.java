package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SysFundBO {

    private long id;
    //用户id
    private long uid;
    private String nickname;
    //企业ID
    private long cid;
    private String companyname;
    //类型 1 A纸条被抽返给用户A  2 A企业成员账号被抽返给A企业管理员 3 邀请用户被抽返给被邀请人 4 提现已拨款
    private int type;
    //资金数量
    private double fund;
    //关联id type 123 paperid ，4 cashoutid
    private int alongid;
    //序列号，同一序列号代表同一奖励体系
    private String serialno;
    //1 收入  2 支出【提现】
    private int direction;
    //说明
    private String desc;
    private Date created_at;

    private Date updated_at;

}
