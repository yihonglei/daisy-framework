package com.mhjy.pojo.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class SysAddImageDto {
    private long id;

    //位置
    private int place;
    //banner名称
    private String title;
    //路径
    private String src;
    //顺序
    private int sort;
    //状态
    private int enable;
    //类型 1 无跳转  2 链接
    private int type;
    //link
    private String link;

    private Date start_at;

    private Date end_at;

}
