package io.github.rafaelaperruci.moviecataloginfo_api.repository;

import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
