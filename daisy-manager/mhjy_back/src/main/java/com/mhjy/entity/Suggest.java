package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Suggest {
    private long id;
    // 建议人的uid
    private long suggest_uid;
    // 建议内容
    private String content;

    private Date created_at;
    private Date updated_at;
}
