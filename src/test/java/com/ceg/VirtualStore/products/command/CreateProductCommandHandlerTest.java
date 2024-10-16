package com.ceg.VirtualStore.products.command;

import com.ceg.VirtualStore.products.application.command.CreateProductCommand;
import com.ceg.VirtualStore.products.application.command.CreateProductCommandHandler;
import com.ceg.VirtualStore.products.domain.entity.Product;
import com.ceg.VirtualStore.products.domain.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CreateProductCommandHandlerTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandle() throws Exception {
        CreateProductCommand command = new CreateProductCommand("productTest", 123.50, 1, "product test");

        createProductCommandHandler.handle(command);

        verify(productRepository, times(1)).save(any(Product.class));
    }
}
