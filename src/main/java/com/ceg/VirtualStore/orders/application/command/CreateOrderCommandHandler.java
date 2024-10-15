package com.ceg.VirtualStore.orders.application.command;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.shared.command.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {

    private final IOrderRepository orderRepository;

    public CreateOrderCommandHandler(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) throws Exception {
        Order order = new Order(null, UUID.fromString(command.getUserId()), null);
        System.out.println("getUserId: " + order.getUserId());
        orderRepository.save(order);
    }
}
