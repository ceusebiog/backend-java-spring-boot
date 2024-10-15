package com.ceg.VirtualStore.products.infrastructure.repository;

import com.ceg.VirtualStore.products.domain.entity.Product;
import com.ceg.VirtualStore.products.domain.repository.IProductRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDBProductRepository implements IProductRepository {
    private final String tableName = "dev_Products";
    private final DynamoDbClient dynamoDbClient;

    public DynamoDBProductRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void save(Product product) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(this.tableName)
                .item(Map.of(
                        "productId", AttributeValue.builder().s(product.getProductId().toString()).build(),
                        "name", AttributeValue.builder().s(product.getName()).build(),
                        "description", AttributeValue.builder().s(product.getDescription()).build(),
                        "price", AttributeValue.builder().n(String.valueOf(product.getPrice())).build(),
                        "stock", AttributeValue.builder().n(String.valueOf(product.getStock())).build(),
                        "productState", AttributeValue.builder().s(product.getProductState().name()).build()
                ))
                .build();

        dynamoDbClient.putItem(request);
    }

    @Override
    public void update(String productId, Map<String, Object> mapUpdatedValues) {
        HashMap<String, AttributeValueUpdate> updatedValues = new HashMap<>();

        for (var entry : mapUpdatedValues.entrySet())
        {
            updatedValues.put(entry.getKey(), AttributeValueUpdate.builder()
                    .value(AttributeValue.builder().s(entry.getValue().toString()).build())
                    .action(AttributeAction.PUT)
                    .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(this.tableName)
                .key(Map.of("productId", AttributeValue.builder().s(productId).build()))
                .attributeUpdates(updatedValues)
                .build();

        dynamoDbClient.updateItem(request);
    }
}
