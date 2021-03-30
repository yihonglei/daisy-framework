package com.jpeony.shopping.dto;


import com.jpeony.commons.result.AbstractRequest;
import lombok.Data;

@Data
public class AllProductCateRequest extends AbstractRequest {
    private String sort;

    @Override
    public void requestCheck() {

    }
}
