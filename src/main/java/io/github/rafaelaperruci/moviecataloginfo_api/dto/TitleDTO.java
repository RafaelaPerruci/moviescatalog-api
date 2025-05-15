package io.github.rafaelaperruci.moviecataloginfo_api.dto;

import jakarta.validation.constraints.NotBlank;

public record TitleDTO(
        @NotBlank(message = "O título é obrigatório e não pode estar em branco.")
        String title
) {
}
