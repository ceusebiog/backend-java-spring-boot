package com.ceg.VirtualStore.shared.query;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}
