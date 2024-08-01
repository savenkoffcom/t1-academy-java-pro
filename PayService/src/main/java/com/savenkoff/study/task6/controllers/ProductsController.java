package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.configurations.properties.ExecutorsProperties;
import com.savenkoff.study.task6.dto.UserProductsDTO;
import com.savenkoff.study.task6.services.ProductsExecutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final ProductsExecutorService productsExecutorService;
    private final ExecutorsProperties executorsProperties;

    public ProductsController(ProductsExecutorService productsExecutorService, ExecutorsProperties executorsProperties) {
        this.productsExecutorService = productsExecutorService;
        this.executorsProperties = executorsProperties;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProductsDTO> getUserProducts(@PathVariable("userId") Long userId) {
        ResponseEntity<UserProductsDTO> response = productsExecutorService.getRestTemplateForProducts().getForEntity(
                executorsProperties.getProductsExecutorService().getUrl() + "/api/v1/users/"+ userId + "/products",
                UserProductsDTO.class
                );
        return response;
    }
}
