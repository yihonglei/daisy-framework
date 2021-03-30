package com.jpeony.user.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

@Data
public class CheckAuthResponse extends AbstractResponse {

    private String userinfo;
}
