package com.ceg.VirtualStore.shared.query;

public interface QueryHandler<T, U extends Query<T>> {
    T handle(U query) throws Exception;
}
