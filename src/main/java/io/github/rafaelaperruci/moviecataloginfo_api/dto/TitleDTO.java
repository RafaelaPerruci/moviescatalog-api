package io.github.rafaelaperruci.moviecataloginfo_api.dto;

import jakarta.validation.constraints.NotBlank;

public record TitleDTO(
        @NotBlank
        String name
) {
}
