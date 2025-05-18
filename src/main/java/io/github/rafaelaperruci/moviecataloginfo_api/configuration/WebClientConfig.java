package io.github.rafaelaperruci.moviecataloginfo_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebClient {

    @Bean
    public org.springframework.web.reactive.function.client.WebClient webClient(org.springframework.web.reactive.function.client.WebClient.Builder builder) {
        return builder
                .defaultHeader("Content-Type", "application/json") // opcional
                .build();
    }
}
