package com.jpeony.lotus.core.pojo.bo;

import lombok.Data;

@Data
public class HeaderBO {
    private String token;
    /**
     * 1 - ios 2 - android 3 - h5
     */
    private String ostype;
}
