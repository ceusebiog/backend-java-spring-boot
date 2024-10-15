package com.ceg.VirtualStore.shared.command;

public interface CommandBus {
    void handle(Command command) throws Exception;
}
