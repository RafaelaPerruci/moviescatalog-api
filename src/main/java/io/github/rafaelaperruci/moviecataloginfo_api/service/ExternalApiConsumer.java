package io.github.rafaelaperruci.moviecataloginfo_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ExternalApiConsumer {

    @Value("${TOKEN_TMDB}")
    private String token;

    private ObjectMapper mapper;
    private WebClient webClient;


    public ExternalApiConsumer(ObjectMapper mapper, WebClient webClient) {
        this.mapper = mapper;
        this.webClient = webClient;
    }

    public MovieDTO getMovieFromExternalApi(String movieTitle) {
        try {
            String encodedTitle = URLEncoder.encode(movieTitle, StandardCharsets.UTF_8);
            String uri = "/search/movie?query=" + encodedTitle + "&language=pt-BR";

            String responseBody = webClient
                    .get()
                    .uri(uri)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            clientResponse -> Mono.error(new IllegalStateException("Resposta da API inválida.")))
                    .bodyToMono(String.class)
                    .block();

            JsonNode jsonNode = mapper.readTree(responseBody);

            if (jsonNode.has("results") && jsonNode.get("results").isArray() && !jsonNode.get("results").isEmpty()) {
                JsonNode results = jsonNode.get("results").get(0);
                return mapper.convertValue(results, MovieDTO.class);
            } else {
                throw new IllegalStateException("Nenhum resultado encontrado.");
            }

        }
        catch (IOException e){
            throw new RuntimeException("Erro ao ler o JSON da API externa: " + e.getMessage(), e);
        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao consumir a API externa: " + e.getMessage(), e);
        }
    }

}
