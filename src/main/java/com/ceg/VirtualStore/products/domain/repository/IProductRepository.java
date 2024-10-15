package com.ceg.VirtualStore.products.domain.repository;

import com.ceg.VirtualStore.products.domain.entity.Product;

import java.util.Map;

public interface IProductRepository {
    void save(Product product);
    void update(String productId, Map<String, Object> mapUpdatedValues);
}
