package com.savenkoff.study.task6.configurations.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("application.properties")
public class ApplicationProperties {
    private boolean httpDebug;
}
