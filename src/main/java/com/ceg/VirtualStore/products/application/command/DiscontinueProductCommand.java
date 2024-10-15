package com.ceg.VirtualStore.products.application.command;

import com.ceg.VirtualStore.shared.command.Command;

public class DiscontinueProductCommand extends Command {
    private String productId;

    public String getProductId() {
        return productId;
    }

    public DiscontinueProductCommand(String productId) {
        this.productId = productId;
    }
}
