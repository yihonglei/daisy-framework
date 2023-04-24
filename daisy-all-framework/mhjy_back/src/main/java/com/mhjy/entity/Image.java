package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Image {
    private long id;
    //banner名称
    private String title;
    //路径
    private String src;
    //顺序
    private int sort;
    //位置
    private int place;
    //状态
    private int enable;
    //类型 1 无跳转  2 链接
    private int type;
    //link
    private String link;

    private Date start_at;

    private Date end_at;

    private Date created_at;

    private Date updated_at;
}
