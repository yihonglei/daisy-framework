package com.jpeony.shopping.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

@Data
public class ProductDetailResponse extends AbstractResponse {
    private ProductDetailDto productDetailDto;
}
