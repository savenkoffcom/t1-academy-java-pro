package com.savenkoff.study.task6.configurations;

import com.savenkoff.study.task6.configurations.properties.ApplicationProperties;
import com.savenkoff.study.task6.configurations.properties.ExecutorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({ExecutorsProperties.class, ApplicationProperties.class})
public class ApplicationConfig {

    private final ExecutorsProperties executorsProperties;

    public ApplicationConfig(ExecutorsProperties executorsProperties) {
        this.executorsProperties = executorsProperties;
    }

    @Bean
    public RestTemplate restTemplateForProductsService(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(executorsProperties.getProductsExecutorService().getConnectTimeout())
                .setReadTimeout(executorsProperties.getProductsExecutorService().getReadTimeout())
                .build();
    }
}
