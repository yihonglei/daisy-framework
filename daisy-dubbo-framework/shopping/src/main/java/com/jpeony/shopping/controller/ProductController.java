package com.jpeony.shopping.controller;

import com.jpeony.commons.core.ResponseData;
import com.jpeony.commons.core.ResponseUtil;
import com.jpeony.shopping.IProductService;
import com.jpeony.shopping.constants.ShoppingRetCode;
import com.jpeony.shopping.dto.*;
import com.jpeony.shopping.form.PageInfo;
import com.jpeony.shopping.form.PageResponse;
import com.jpeony.user.annotation.Anoymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/shopping")
@Api(tags = "ProductController", description = "商品控制层")
public class ProductController {

    @Reference(timeout = 3000)
    IProductService productService;

    @Anoymous
    @GetMapping("/product/{id}")
    @ApiOperation("查询商品详情")
    @ApiImplicitParam(name = "id", value = "商品ID", paramType = "path", required = true)
    public ResponseData product(@PathVariable long id) {
        ProductDetailRequest request = new ProductDetailRequest();
        request.setId(id);
        ProductDetailResponse response = productService.getProductDetail(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getProductDetailDto());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

    /**
     * 返回商品列表
     *
     * @param pageInfo
     * @return
     */
    @Anoymous
    @GetMapping("/goods")
    @ApiOperation("查询商品列表")
    @ApiImplicitParam(name = "pageInfo", value = "分页信息", dataType = "PageInfo", required = true)
    public ResponseData goods(PageInfo pageInfo) {
        AllProductRequest request = new AllProductRequest();
        request.setCid(pageInfo.getCid());
        request.setPage(pageInfo.getPage());
        request.setPriceGt(pageInfo.getPriceGt());
        request.setPriceLte(pageInfo.getPriceLte());
        request.setSize(pageInfo.getSize());
        request.setSort(pageInfo.getSort());
        AllProductResponse response = productService.getAllProduct(request);
        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            PageResponse pageResponse = new PageResponse();
            pageResponse.setData(response.getProductDtoList());
            pageResponse.setTotal(response.getTotal());
            return new ResponseUtil().setData(pageResponse);
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

    /**
     * 返回推荐的商品
     *
     * @return
     */
    @Anoymous
    @GetMapping("/recommend")
    @ApiOperation("查询推荐的商品")
    public ResponseData recommend() {
        RecommendResponse response = productService.getRecommendGoods();
        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getPanelContentItemDtos());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

}
