package com.jpeony.shopping;

import com.jpeony.shopping.dto.*;
import com.jpeony.shopping.dto.*;

/**
 * 商品信息服务接口
 * 查询所有商品，以及商品详情
 */
public interface IProductService {

    /**
     * 查看商品明细
     * @param request
     * @return
     */
    ProductDetailResponse getProductDetail(ProductDetailRequest request);

    /**
     * 查询所有商品（分页）
     * @param request
     * @return
     */
    AllProductResponse getAllProduct(AllProductRequest request);

    /**
     * 获取推荐的商品板块
     * @return
     */
    RecommendResponse getRecommendGoods();

}
