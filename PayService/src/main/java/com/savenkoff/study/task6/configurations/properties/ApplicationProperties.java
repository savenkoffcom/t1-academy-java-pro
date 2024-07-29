package com.savenkoff.study.task6.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.properties")
public class ApplicationProperties {

    private boolean httpDebug;

    public boolean isHttpDebug() {
        return httpDebug;
    }

    public void setHttpDebug(boolean httpDebug) {
        this.httpDebug = httpDebug;
    }
}
