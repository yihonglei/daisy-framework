package com.jpeony.search.services;

import com.jpeony.search.InitDataService;
import com.jpeony.search.converter.ProductConverter;
import com.jpeony.search.dal.entitys.Item;
import com.jpeony.search.dal.persistence.ItemMapper;
import com.jpeony.search.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@Service
public class InitDataServiceImpl implements InitDataService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ProductConverter productConverter;

    @Override
    public void initItems() {
        List<Item> items=itemMapper.selectAll();
        items.parallelStream().forEach(item->{
            productRepository.save(productConverter.item2Document(item));
        });
    }
}
