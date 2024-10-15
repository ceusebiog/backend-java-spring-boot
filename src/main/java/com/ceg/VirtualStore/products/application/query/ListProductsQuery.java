package com.ceg.VirtualStore.products.application.query;

import com.ceg.VirtualStore.products.domain.entity.Product;
import com.ceg.VirtualStore.shared.query.Query;

import java.util.List;

public class ListProductsQuery extends Query<List<Product>> {
    private int limit;
    private String exclusiveStartKey;

    public int getLimit() {
        return limit;
    }

    public String getExclusiveStartKey() {
        return exclusiveStartKey;
    }

    public ListProductsQuery(int limit, String exclusiveStartKey) {
        this.limit = limit;
        this.exclusiveStartKey = exclusiveStartKey;
    }
}
