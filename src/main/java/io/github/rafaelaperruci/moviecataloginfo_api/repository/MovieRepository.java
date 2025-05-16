package io.github.rafaelaperruci.moviecataloginfo_api.repository;

import io.github.rafaelaperruci.moviecataloginfo_api.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Page<Movie> findAll(Pageable pageable);
}
