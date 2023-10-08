package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.Date;

@Data
public class SuggestBO {
    private long id;
    // 建议人的uid
    private long suggest_uid;
    // 建议人的uid
    private String suggest_nickname;
    // 建议内容
    private String content;

    private Date created_at;
    private Date updated_at;
}
