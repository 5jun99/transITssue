package com.project.transit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fetch.api")
@Data
public class FetchApiProperties {
    private String baseUrl;
    private String apiKey;
    private long fetchInterval;
    private Accident accident;

    @Data
    public static class Accident {
        private String infoUrl;
        private String typeUrl;
    }
}