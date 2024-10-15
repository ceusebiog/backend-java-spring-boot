package com.ceg.VirtualStore.products.application.command;

import com.ceg.VirtualStore.products.domain.repository.IProductRepository;
import com.ceg.VirtualStore.shared.command.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

    private final IProductRepository productRepository;

    public UpdateProductCommandHandler(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void handle(UpdateProductCommand command) throws Exception {
        Map<String, Object> updatedValues = new HashMap<>();

        if (command.getName() != null)
            updatedValues.put("name", command.getName());
        if (command.getDescription() != null)
            updatedValues.put("description", command.getDescription());
        if (command.getPrice() != null)
            updatedValues.put("price", command.getPrice());

        productRepository.update(command.getProductId(), updatedValues);
    }
}
