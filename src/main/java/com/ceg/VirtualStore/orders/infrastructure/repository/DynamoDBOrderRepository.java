package com.ceg.VirtualStore.orders.infrastructure.repository;

import com.ceg.VirtualStore.orders.domain.entity.Order;
import com.ceg.VirtualStore.orders.domain.repository.IOrderRepository;
import com.ceg.VirtualStore.orders.domain.value_object.OrderStatus;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

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

    @Override
    public List<Order> findByUserId(String userId){
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#userId","userId");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":userIdValue", AttributeValue.builder().s(userId).build());

        QueryRequest request = QueryRequest.builder()
                .tableName(this.tableName)
                .indexName("UserIdIndex")
                .keyConditionExpression("#userId = :userIdValue")
                .expressionAttributeNames(expressionAttributeNames)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        QueryResponse response = dynamoDbClient.query(request);

        if (response.hasItems()) {
            List<Order> orderList = new ArrayList<>();

            for (Map<String, AttributeValue> item: response.items()){
                orderList.add(new Order(
                        UUID.fromString(item.get("orderId").s()),
                        UUID.fromString(item.get("userId").s()),
                        OrderStatus.valueOf(item.get("status").s())
                ));
            }

            return orderList;
        }

        return List.of();
    }
}
