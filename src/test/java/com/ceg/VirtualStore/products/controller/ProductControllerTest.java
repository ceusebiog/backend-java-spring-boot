package com.ceg.VirtualStore.products.controller;

import com.ceg.VirtualStore.products.application.command.CreateProductCommand;
import com.ceg.VirtualStore.products.application.dto.CreateProductDto;
import com.ceg.VirtualStore.products.infrastructure.controller.ProductController;
import com.ceg.VirtualStore.shared.command.CommandBus;
import com.ceg.VirtualStore.shared.query.QueryBus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandBus commandBus;
    @MockBean
    private QueryBus queryBus;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() throws Exception {
        CreateProductDto dto = new CreateProductDto();
        dto.setName("productTest");
        dto.setPrice(123.50);
        dto.setStock(1);
        dto.setDescription("product test");

        CreateProductCommand command = new CreateProductCommand(dto.getName(), dto.getPrice(), dto.getStock(), dto.getDescription());

        doNothing().when(commandBus).handle(command);

        final ResultActions result = mockMvc.perform(post("/api/product")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));
    }
}
