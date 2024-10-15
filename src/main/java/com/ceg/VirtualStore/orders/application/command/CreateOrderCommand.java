package com.ceg.VirtualStore.orders.application.command;

import com.ceg.VirtualStore.shared.command.Command;

public class CreateOrderCommand extends Command {
    private final String userId;

    public String getUserId() {
        return userId;
    }

    public CreateOrderCommand(String userId) {
        this.userId = userId;
    }
}
