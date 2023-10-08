package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SysTradeBO {
    private long id;

    private long uid;

    private String username;

    private long cid;

    /**
     * 1 vip  2 代理续费 3 抽纸条 4 放纸条
     */
    private int type;

    /**
     * 1、2商品ID  3 抽到的纸条UID 4 当前用户的UID
     */
    private long along_id;

    private String trade_no;

    // 多少钱 分
    private double fen;

    private String prepay_id;

    private String status;

    private Date created_at;

    private Date updated_at;


}
