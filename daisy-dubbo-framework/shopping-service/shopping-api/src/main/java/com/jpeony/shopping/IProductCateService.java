package com.jpeony.shopping;

import com.jpeony.shopping.dto.AllProductCateRequest;
import com.jpeony.shopping.dto.AllProductCateResponse;

public interface IProductCateService {
    /**
     * 获取所有产品分类
     */
    AllProductCateResponse getAllProductCate(AllProductCateRequest request);
}
