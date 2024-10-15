package com.ceg.VirtualStore.shared.command;

public interface CommandHandler <T extends Command>{
    void handle(T command) throws Exception;
}
