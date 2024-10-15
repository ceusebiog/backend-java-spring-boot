package com.ceg.VirtualStore.orders.application.query;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.shared.query.Query;

public class GetOrderByIdQuery extends Query<Order> {
    private final String orderId;

    public String getOrderId() {
        return orderId;
    }

    public GetOrderByIdQuery(String orderId) {
        this.orderId = orderId;
    }
}
