package com.jpeony.shopping.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

@Data
public class ProductDetailResponse extends AbstractResponse {
    private ProductDetailDto productDetailDto;
}
