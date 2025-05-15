package io.github.rafaelaperruci.moviecataloginfo_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDTO (
        @JsonAlias("original_title") String title,
        @JsonAlias("overview") String resume,
        @JsonAlias("release_date") String date,
        @JsonAlias("vote_average") Integer rating
){

}
