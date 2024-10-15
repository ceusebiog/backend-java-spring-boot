package com.ceg.VirtualStore.products.application.query;

import com.ceg.VirtualStore.products.domain.entity.Product;
import com.ceg.VirtualStore.shared.query.Query;

public class GetProductDetailsQuery extends Query<Product> {
    private String productId;

    public String getProductId() {
        return productId;
    }

    public GetProductDetailsQuery(String productId) {
        this.productId = productId;
    }
}
