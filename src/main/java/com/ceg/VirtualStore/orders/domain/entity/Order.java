package com.ceg.VirtualStore.orders.domain.entity;

import com.ceg.VirtualStore.orders.domain.value_object.OrderStatus;

import java.util.Optional;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final UUID userId;
    private OrderStatus status;
    //  TotalItems
    //  TotalAmount
    //  ProductIds
    //  CreatedDate
    //  UpdatedDate
    //  ShippingAddress
    //  UserPhone
    public UUID getOrderId() {
        return orderId;
    }

    public UUID getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order(UUID orderId, UUID userId, OrderStatus status) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
    }
}
