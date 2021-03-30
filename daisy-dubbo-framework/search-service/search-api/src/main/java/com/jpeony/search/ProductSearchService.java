package com.jpeony.search;

import com.jpeony.search.dto.SearchRequest;
import com.jpeony.search.dto.SearchResponse;

/**
 * 商城全部商品搜索API推荐放在此接口中
 */
public interface ProductSearchService {
    /**
     * 搜索商品 精准搜索
     *
     * @param request request
     * @return SearchResponse
     */
    SearchResponse search(SearchRequest request);

    /**
     * 搜索商品 模糊搜索
     *
     * @param request request
     * @return SearchResponse
     */
    SearchResponse fuzzySearch(SearchRequest request);

    /**
     * 商品热门搜索关键字 **热搜推荐**
     *
     * @return SearchResponse
     */
    SearchResponse hotProductKeyword();

}
