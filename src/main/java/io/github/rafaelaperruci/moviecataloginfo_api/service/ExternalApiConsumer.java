package io.github.rafaelaperruci.moviecataloginfo_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ExternalApiConsumer {

    @Value("${TOKEN_TMDB}")
    private String token;

    private ObjectMapper mapper;

    public ExternalApiConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public JsonNode getMovieData(String movieTitle) {
        JsonNode jsonNode = null;
        try {
            String encodedTitle = java.net.URLEncoder.encode(movieTitle, java.nio.charset.StandardCharsets.UTF_8);
            String apiUrl = "https://api.themoviedb.org/3/search/movie?query=" + encodedTitle + "&language=pt-BR";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {
                jsonNode = mapper.readTree(response.body());
                System.out.println(jsonNode.toString());
            } else {
                System.err.println("Erro: status " + response.statusCode());
            }

        } catch (IOException e) {
            System.err.println("Erro ao consumir API externa: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return jsonNode;
    }
}
