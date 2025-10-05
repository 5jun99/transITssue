package com.project.transit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final FetchApiProperties fetchApiProperties;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(fetchApiProperties.getBaseUrl())
                .defaultHeader("Content-Type", "application/xml")
                .build();
                //.defaultHeader("Authorization", "API_KEY")  // 필요하다면 추가
    }
}
