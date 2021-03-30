package com.jpeony.search.dto;

import com.jpeony.commons.result.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.search.consts.SearchRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 通用请求类
 */
@Data
public class SearchRequest extends AbstractRequest {

    private String keyword;
    private Integer currentPage;
    private Integer pageSize;
    private String sort;
    private Integer priceGt;
    private Integer priceLte;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(keyword)){
            throw new ValidateException(
                    SearchRetCode.REQUEST_CHECK_FAILURE.getCode(),
                    SearchRetCode.REQUEST_CHECK_FAILURE.getMsg());
        }
    }


    @Override
    public String toString() {
        return "SearchRequest{" +
                "keyword='" + keyword + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
