package com.ceg.VirtualStore.orders.application.query;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.shared.query.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetOrderByIdQueryHandler implements QueryHandler<Order, GetOrderByIdQuery> {

    private final IOrderRepository orderRepository;

    public GetOrderByIdQueryHandler(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order handle(GetOrderByIdQuery query) throws Exception {
        System.out.println("ir");
        Optional<Order> oOrder = orderRepository.findById(query.getOrderId());
        System.out.println("out");
        return oOrder.orElse(null);
    }
}
