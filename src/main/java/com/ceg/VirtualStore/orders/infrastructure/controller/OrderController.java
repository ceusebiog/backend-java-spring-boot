package com.ceg.VirtualStore.orders.infrastructure.controller;

import com.ceg.VirtualStore.orders.application.command.CreateOrderCommand;
import com.ceg.VirtualStore.orders.application.dto.CreateOrderDto;
import com.ceg.VirtualStore.orders.application.query.GetOrderByIdQuery;
import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.shared.command.CommandBus;
import com.ceg.VirtualStore.shared.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
public class OrderController {

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    @PostMapping
    @ResponseBody
    public ResponseEntity createOrder(@RequestBody CreateOrderDto createOrderDto) throws Exception {
        commandBus.handle(new CreateOrderCommand(createOrderDto.getUserId()));

        return ResponseEntity.created(URI.create("")).build();
    }


    @GetMapping("/details/{orderId}")
    @ResponseBody
    public ResponseEntity<Object> getOrderById(@PathVariable("orderId") String orderId) throws Exception {
        Order order = queryBus.handle(new GetOrderByIdQuery(orderId));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
