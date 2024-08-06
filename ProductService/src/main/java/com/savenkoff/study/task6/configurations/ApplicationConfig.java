package com.savenkoff.study.task6.configurations;

import com.savenkoff.study.task6.configurations.properties.ApplicationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class ApplicationConfig {
}
