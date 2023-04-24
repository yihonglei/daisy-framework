package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class ImageBO {

    private long id;
    //banner名称
    private String title;
    //路径
    private String src;
    //位置
    private int place;
    //顺序
    private int sort;
    //状态
    private int enable;
    //类型
    private int type;
    //link
    private String link;

    private Date start_at;
    private Date end_at;

    private String created_time;

    private Date created_at;

    private Date updated_at;
}
