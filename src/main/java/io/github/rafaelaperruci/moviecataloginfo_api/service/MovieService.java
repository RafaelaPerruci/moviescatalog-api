package io.github.rafaelaperruci.moviecataloginfo_api.service;

import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.dto.MovieResponseDTO;
import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;
import io.github.rafaelaperruci.moviecataloginfo_api.repository.MovieRepository;
import jakarta.persistence.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;

@Service
public class MovieService {

    private ExternalApiConsumer externalApiConsumer;
    private MovieRepository repository;
    private DateFormatter dateFormatter;

    public MovieService(){}

    public MovieService(ExternalApiConsumer externalApiConsumer, MovieRepository repository, DateFormatter dateFormatter) {
        this.externalApiConsumer = externalApiConsumer;
        this.repository = repository;
        this.dateFormatter = dateFormatter;
    }

    public MovieService(ExternalApiConsumer mockConsumer) {
        this.externalApiConsumer = mockConsumer;
    }

    public MovieDTO getMovieData(String name) {
        return externalApiConsumer.getMovieFromExternalApi(name);
    }
    public Movie registerMovie(MovieDTO movieDTO) {

        String formatedDate = movieDTO.date();
        if (formatedDate == null) {
            throw new RuntimeException("Data de lançamento não pode ser nula.");
        }

        Movie movie = new Movie(movieDTO);
        movie.setId(UUID.randomUUID().toString());
        movie.setReleaseDate(formatDate(movieDTO.date()));
        return repository.save(movie);
    }

    private String formatDate(String date) {
        try {
            return dateFormatter.format(date);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Formato de data inválido. Esperado: yyyy-MM-dd.");
        }
    }

    public Page<MovieResponseDTO> getAllMovies(Pageable pagination) {
        Page<Movie> moviesPage = repository.findAll(pagination);
        if (moviesPage.isEmpty()) {
            return Page.empty();
        }
        return moviesPage.map(MovieResponseDTO::new);
    }

    public MovieResponseDTO findMovieById(String id) {
        Movie movie = repository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }
        return new MovieResponseDTO(movie);
    }

    public Optional<Movie> deleteMovieById(String id) {
        Optional<Movie> movie = repository.findById(id);
        if (movie.isEmpty()) {
            return Optional.empty();
        }
        repository.deleteById(id);
        return movie;
    }
}
