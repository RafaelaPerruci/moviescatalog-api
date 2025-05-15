package io.github.rafaelaperruci.moviecataloginfo_api.service;

import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private ExternalApiConsumer externalApiConsumer;

    public MovieService(ExternalApiConsumer externalApiConsumer) {
        this.externalApiConsumer = externalApiConsumer;
    }

    public MovieDTO getMovieData(String name) {
        return externalApiConsumer.getMovieFromExternalApi(name);
    }

//    public Movie fromMovie(JsonNode jsonNode) {
//        if (jsonNode.isArray() && jsonNode.size() > 1) {
//
//        }
//    }
}
