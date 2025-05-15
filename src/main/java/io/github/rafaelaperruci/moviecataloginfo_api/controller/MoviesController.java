package io.github.rafaelaperruci.moviecataloginfo_api.controller;

import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieData;
import io.github.rafaelaperruci.moviecataloginfo_api.service.ExternalApiConsumer;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private ExternalApiConsumer externalApiConsumer;

    public MoviesController(ExternalApiConsumer externalApiConsumer) {
        this.externalApiConsumer = externalApiConsumer;
    }

    @PostMapping
    public void register(@RequestBody MovieData movieData){
        externalApiConsumer.getMovieData(movieData.name());
        System.out.println(movieData.name());
    }
}
