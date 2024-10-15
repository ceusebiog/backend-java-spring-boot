package com.ceg.VirtualStore.orders.application.query;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.shared.query.QueryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class GetOrderByIdQueryHandler implements QueryHandler<Order, GetOrderByIdQuery> {

    private final IOrderRepository orderRepository;

    public GetOrderByIdQueryHandler(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order handle(GetOrderByIdQuery query) throws Exception {
        Optional<Order> oOrder = orderRepository.findById(query.getOrderId());
        if (oOrder.isPresent()) {
            return oOrder.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Order not found with orderId: %s", query.getOrderId()));
        }
    }
}
