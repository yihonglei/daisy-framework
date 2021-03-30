package com.jpeony.search.repository;

import com.jpeony.search.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ItemDocument, Integer> {
}
