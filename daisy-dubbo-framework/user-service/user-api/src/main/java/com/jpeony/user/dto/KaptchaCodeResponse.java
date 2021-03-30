package com.jpeony.user.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

@Data
public class KaptchaCodeResponse extends AbstractResponse {

    private String imageCode;

    private String uuid;


}
