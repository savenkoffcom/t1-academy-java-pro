package com.savenkoff.study.task6.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("integrations.executors")
public class ExecutorsProperties {
    private final RestTemplateProperties productsExecutorService;

    @ConstructorBinding
    public ExecutorsProperties(RestTemplateProperties productsExecutorService) {
        this.productsExecutorService = productsExecutorService;
    }

    public RestTemplateProperties getProductsExecutorService() {
        return productsExecutorService;
    }
}
