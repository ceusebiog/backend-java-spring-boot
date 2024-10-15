package com.ceg.VirtualStore.products.application.command;

import com.ceg.VirtualStore.products.domain.entity.Product;
import com.ceg.VirtualStore.products.domain.repository.IProductRepository;
import com.ceg.VirtualStore.products.domain.value_object.ProductState;
import com.ceg.VirtualStore.shared.command.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

    private final IProductRepository productRepository;

    public CreateProductCommandHandler(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void handle(CreateProductCommand command) throws Exception {
        UUID productId = UUID.randomUUID();
        ProductState productState = ProductState.OUT_OF_STOCK;

        if (command.getStock() > 0)
            productState = ProductState.ACTIVE;

        Product product = new Product(productId, command.getName(), command.getPrice(), command.getStock(), command.getDescription(), productState);

        productRepository.save(product);
    }
}
