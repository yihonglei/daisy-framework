package com.jpeony.user.dto;

import com.jpeony.commons.core.AbstractRequest;
import lombok.Data;

@Data
public class HeadImageRequest extends AbstractRequest {
    private Long userId;
    private String imageData;

    @Override
    public void requestCheck() {

    }
}
