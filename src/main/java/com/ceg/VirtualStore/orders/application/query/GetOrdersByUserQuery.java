package com.ceg.VirtualStore.orders.application.query;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.shared.query.Query;

import java.util.List;

public class GetOrdersByUserQuery extends Query<List<Order>> {
    private final String userId;

    public String getOrderId() {
        return userId;
    }

    public GetOrdersByUserQuery(String userId) {
        this.userId = userId;
    }
}
