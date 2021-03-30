package com.jpeony.shopping.services;

import com.alibaba.fastjson.JSON;
import com.jpeony.shopping.IProductCateService;
import com.jpeony.shopping.constant.GlobalConstants;
import com.jpeony.shopping.constants.ShoppingRetCode;
import com.jpeony.shopping.converter.ProductCateConverter;
import com.jpeony.shopping.dal.entitys.ItemCat;
import com.jpeony.shopping.dal.persistence.ItemCatMapper;
import com.jpeony.shopping.dto.AllProductCateRequest;
import com.jpeony.shopping.dto.AllProductCateResponse;
import com.jpeony.shopping.dto.ProductCateDto;
import com.jpeony.shopping.services.cache.CacheManager;
import com.jpeony.shopping.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class ProductCateServiceImpl implements IProductCateService {
    @Autowired
    ItemCatMapper itemCatMapper;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    ProductCateConverter productCateConverter;

    @Override
    public AllProductCateResponse getAllProductCate(AllProductCateRequest request) {
        AllProductCateResponse response = new AllProductCateResponse();
        response.setCode(ShoppingRetCode.SUCCESS.getCode());
        response.setMsg(ShoppingRetCode.SUCCESS.getMessage());

        try{
            String json=cacheManager.checkCache(GlobalConstants.PRODUCT_CATE_CACHE_KEY);
            if(StringUtils.isNotBlank(json)){
                List<ProductCateDto> productCateDtos = JSON.parseArray(json,ProductCateDto.class);
                response.setProductCateDtoList(productCateDtos);
                return response;
            }
            Example itemCatExample = new Example(ItemCat.class);
//            ItemCatExample itemCatExample = new ItemCatExample();
//            ItemCatExample.Criteria criteria = itemCatExample.createCriteria();
            String orderCol = "sort_order";
            String orderDir = "asc";
            if(request.getSort().equals("1")){
                orderCol="sort_order";
                orderDir="asc";
            }else if(request.getSort().equals("-1")){
                orderCol="sort_order";
                orderDir="desc";
            }
            itemCatExample.setOrderByClause(orderCol + " " + orderDir);

            List<ItemCat> itemCats = itemCatMapper.selectByExample(itemCatExample);
            List<ProductCateDto> productCateDtoList = productCateConverter.items2Dto(itemCats);
            response.setProductCateDtoList(productCateDtoList);
            //设置缓存
            cacheManager.setCache(
                    genProductCateCacheKey(request),
                    JSON.toJSON(productCateDtoList).toString(),
                    GlobalConstants.PRODUCT_CATE_EXPIRE_TIME);
        }catch (Exception e){
            log.error("ProductCateServiceImpl.getAllProductCate Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    private String genProductCateCacheKey(AllProductCateRequest request) {
        return GlobalConstants.PRODUCT_CATE_CACHE_KEY;
    }
}
