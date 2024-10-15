package com.ceg.VirtualStore.products.application.command;

import com.ceg.VirtualStore.shared.command.Command;

public class UpdateProductCommand extends Command {
    private String productId;
    private String name;
    private String price;
    private String description;

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public UpdateProductCommand(String productId, String name, String price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
