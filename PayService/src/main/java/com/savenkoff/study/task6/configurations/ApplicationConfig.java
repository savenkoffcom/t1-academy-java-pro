package com.savenkoff.study.task6.configurations;

import com.savenkoff.study.task6.configurations.properties.ExecutorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@EnableConfigurationProperties(ExecutorsProperties.class)
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
