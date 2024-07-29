package com.savenkoff.study.task6.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductsExecutorService {

    private final RestTemplate restTemplateForProductsService;

    public ProductsExecutorService(RestTemplate restTemplateForProductsService) {
        this.restTemplateForProductsService = restTemplateForProductsService;
    }

    public RestTemplate getRestTemplateForProducts() {
        return restTemplateForProductsService;
    }
}
