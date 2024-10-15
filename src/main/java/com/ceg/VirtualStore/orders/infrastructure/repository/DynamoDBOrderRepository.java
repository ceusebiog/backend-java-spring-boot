package com.ceg.VirtualStore.orders.infrastructure.repository;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.orders.domain.value_object.OrderStatus;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DynamoDBOrderRepository implements IOrderRepository {
    private final String tableName = "dev_Orders";
    private final DynamoDbClient dynamoDbClient;

    public DynamoDBOrderRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void save(Order order) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(this.tableName)
                .item(Map.of(
                        "orderId", AttributeValue.builder().s(order.getOrderId().toString()).build(),
                        "userId", AttributeValue.builder().s(order.getUserId().toString()).build(),
                        "status", AttributeValue.builder().s(order.getStatus().name()).build()
                ))
                .build();

        dynamoDbClient.putItem(request);
        System.out.println("created db");
    }

    @Override
    public Optional<Order> findById(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(this.tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);

        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            Order order = new Order(
                    UUID.fromString(item.get("orderId").s()),
                    UUID.fromString(item.get("userId").s()),
                    OrderStatus.valueOf(item.get("status").s())
            );

            return Optional.of(order);
        }

        return Optional.empty();
    }
}
