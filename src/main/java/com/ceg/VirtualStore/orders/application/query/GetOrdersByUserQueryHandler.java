package com.ceg.VirtualStore.orders.application.query;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.shared.query.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrdersByUserQueryHandler implements QueryHandler<List<Order>, GetOrdersByUserQuery> {

    private final IOrderRepository orderRepository;

    public GetOrdersByUserQueryHandler(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> handle(GetOrdersByUserQuery query) throws Exception {
        return orderRepository.findByUserId(query.getOrderId());
    }
}
