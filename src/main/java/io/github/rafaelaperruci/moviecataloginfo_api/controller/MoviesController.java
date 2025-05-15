package io.github.rafaelaperruci.moviecataloginfo_api.controller;


import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.TitleDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;
import io.github.rafaelaperruci.moviecataloginfo_api.repository.MovieRepository;
import io.github.rafaelaperruci.moviecataloginfo_api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MovieService service;
    private MovieRepository repository;

    public MoviesController(MovieService service, MovieRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody TitleDTO dto){
        try {
          MovieDTO movieDTO = service.getMovieData(dto.title());


          Movie movie = new Movie(movieDTO);
          movie.setId(UUID.randomUUID().toString());
          System.out.println(movie);
          repository.save(movie);

          return ResponseEntity.status(HttpStatus.CREATED).body(movie);

        }catch (IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao registrar o filme.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado ao registrar o filme.");
        }

    }
}
