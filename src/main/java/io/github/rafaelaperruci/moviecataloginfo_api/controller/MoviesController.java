package io.github.rafaelaperruci.moviecataloginfo_api.controller;


import io.github.rafaelaperruci.moviecataloginfo_api.dto.DeletedMovieResourceDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieResponseDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.TitleDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;
import io.github.rafaelaperruci.moviecataloginfo_api.repository.MovieRepository;
import io.github.rafaelaperruci.moviecataloginfo_api.service.DateFormatter;
import io.github.rafaelaperruci.moviecataloginfo_api.service.MovieService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MovieService service;


    public MoviesController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody TitleDTO dto){
        try {
          MovieDTO movieDTO = service.getMovieData(dto.title());
          Movie movie = service.registerMovie(movieDTO);
          return ResponseEntity.status(HttpStatus.CREATED).body(new MovieResponseDTO(movie));

        }catch (IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao registrar o filme.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado ao registrar o filme.");
        }

    }

    @GetMapping
    public ResponseEntity<Page<MovieResponseDTO>> getAllMovies(@ParameterObject @PageableDefault(size = 10, sort = {"title"}) Pageable pagination) {

        Page<MovieResponseDTO> moviesPage = service.getAllMovies(pagination);
        if (moviesPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moviesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable String id) {
        MovieResponseDTO movie = service.findMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DeletedMovieResourceDTO> deleteMovie(@PathVariable String id) {
        Optional<Movie> movie = service.deleteMovieById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DeletedMovieResourceDTO(id, "Filme deletado com sucesso."));
    }
}
