package com.ceg.VirtualStore.orders.application.command;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.orders.domain.value_object.OrderStatus;
import com.ceg.VirtualStore.shared.command.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {

    private final IOrderRepository orderRepository;

    public CreateOrderCommandHandler(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) throws Exception {
        UUID orderId = UUID.randomUUID();

        Order order = new Order(orderId, UUID.fromString(command.getUserId()), OrderStatus.CREATED);

        orderRepository.save(order);
    }
}
