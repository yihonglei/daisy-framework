package com.mhjy.pojo.Bo;

import lombok.Data;

@Data
public class HeaderBO {
    private String token;
    /**
     * 1 - ios 2 - android 3 - h5
     */
    private String ostype;
    private Integer uId;
}
