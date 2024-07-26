package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.dto.ProductShortDTO;
import com.savenkoff.study.task6.dto.UserProductsDTO;
import com.savenkoff.study.task6.exceptions.EmptyObjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final RestTemplate restTemplate;

    public ProductsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProductsDTO> GetUserProducts(@PathVariable("userId") Long userId) {
        ResponseEntity<UserProductsDTO> response = restTemplate.getForEntity(
                "http://localhost:8081/products-app/api/v1/products/user/" + userId,
                UserProductsDTO.class
                );
        return response;
    }
}
