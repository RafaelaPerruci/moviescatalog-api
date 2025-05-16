package io.github.rafaelaperruci.moviecataloginfo_api.dto;


import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;

public record MovieSerializeDTO(
        String id,
        String title,
        String resume,
        String date,
        Integer rating
) {

    public MovieSerializeDTO(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getOverviewResume(), movie.getReleaseDate(), movie.getRating());
    }
}
