package com.ceg.VirtualStore.products.domain.entity;

import com.ceg.VirtualStore.products.domain.value_object.ProductState;

import java.util.UUID;

public class Product {
    private UUID productId;
    private String name;
    private double price;
    private int stock;
    private String description;
    private ProductState productState;

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public ProductState getProductState() {
        return productState;
    }

    public Product(UUID productId, String name, double price, int stock, String description, ProductState productState) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.productState = productState;
    }
}
