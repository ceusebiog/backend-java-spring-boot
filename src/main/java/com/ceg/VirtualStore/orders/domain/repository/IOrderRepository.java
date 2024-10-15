package com.ceg.VirtualStore.orders.domain.repository;

import com.ceg.VirtualStore.orders.domain.entity.Order;

import java.util.Optional;

public interface IOrderRepository {
    void save(Order order);
    Optional<Order> findById(String orderId);
}
