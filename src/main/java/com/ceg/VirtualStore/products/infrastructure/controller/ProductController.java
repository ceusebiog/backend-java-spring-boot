package com.ceg.VirtualStore.products.infrastructure.controller;

import com.ceg.VirtualStore.products.application.command.CreateProductCommand;
import com.ceg.VirtualStore.products.application.command.UpdateProductCommand;
import com.ceg.VirtualStore.products.application.dto.CreateProductDto;
import com.ceg.VirtualStore.products.application.dto.UpdateProductDto;
import com.ceg.VirtualStore.shared.command.CommandBus;
import com.ceg.VirtualStore.shared.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product", produces = "application/json")
public class ProductController {

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody CreateProductDto createProductDto) throws Exception {
        commandBus.handle(new CreateProductCommand(createProductDto.getName(), createProductDto.getPrice(), createProductDto.getStock(), createProductDto.getDescription()));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable("productId") String productId, @RequestBody UpdateProductDto updateProductDto) throws Exception {
        commandBus.handle(new UpdateProductCommand(productId, updateProductDto.getName(), updateProductDto.getPrice(), updateProductDto.getDescription()));

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
