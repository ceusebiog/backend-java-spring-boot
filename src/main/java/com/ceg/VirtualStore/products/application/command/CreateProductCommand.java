package com.ceg.VirtualStore.products.application.command;

import com.ceg.VirtualStore.shared.command.Command;

public class CreateProductCommand extends Command {
    private String name;
    private double price;
    private int stock;
    private String description;

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

    public CreateProductCommand(String name, double price, int stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
