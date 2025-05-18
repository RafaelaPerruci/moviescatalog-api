package io.github.rafaelaperruci.moviecataloginfo_api.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    @Primary
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
