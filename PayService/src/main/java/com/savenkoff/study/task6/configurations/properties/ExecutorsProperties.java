package com.savenkoff.study.task6.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integrations.executors")
public class ExecutorsProperties {

    private RestTemplateProperties restTemplateProperties;

    public void setProductsExecutorService(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }

    public RestTemplateProperties getProductsExecutorService() {
        return restTemplateProperties;
    }
}
