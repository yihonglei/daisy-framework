package com.jpeony.search.converter;

import com.jpeony.search.dal.entitys.Item;
import com.jpeony.search.dto.ProductDto;
import com.jpeony.search.entity.ItemDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    @Mappings({
            @Mapping(source = "id", target = "productId"),
            @Mapping(source = "title", target = "productName"),
            @Mapping(source = "price", target = "salePrice"),
            @Mapping(source = "sell_point", target = "subTitle"),
            @Mapping(source = "image", target = "picUrl")
    })
    ProductDto item2Dto(ItemDocument item);

    List<ProductDto> items2Dto(List<ItemDocument> items);

    @Mappings({
            @Mapping(source = "sellPoint", target = "sell_point"),
            @Mapping(source = "limitNum", target = "limit_num")
    })
    ItemDocument item2Document(Item item);
}
