package io.github.rafaelaperruci.moviecataloginfo_api.controller;


import io.github.rafaelaperruci.moviecataloginfo_api.dto.TitleDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MovieService service;

    public MoviesController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public void register(@Valid @RequestBody TitleDTO dto){
        service.getMovieData(dto.title());
        System.out.println(dto.title());
    }
}
