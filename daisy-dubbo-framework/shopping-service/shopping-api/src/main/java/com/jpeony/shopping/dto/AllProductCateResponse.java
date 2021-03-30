package com.jpeony.shopping.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class AllProductCateResponse extends AbstractResponse {
    private List<ProductCateDto> productCateDtoList;
}
