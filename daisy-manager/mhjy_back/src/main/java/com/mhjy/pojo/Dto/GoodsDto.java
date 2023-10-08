package com.mhjy.pojo.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsDto {
    private long id;
    //商品名称
    private String good_name;
    //购买时长 1 3 6 12 / 6 12 24 36
    private String buy_time;
    //售价
    private double sale_price;
    //1 VIP商品  2 续费商品
    private int type;
    //顺序
    private int sort;
    //状态
    private int enable;

    private Date created_at;

    private Date updated_at;
}
