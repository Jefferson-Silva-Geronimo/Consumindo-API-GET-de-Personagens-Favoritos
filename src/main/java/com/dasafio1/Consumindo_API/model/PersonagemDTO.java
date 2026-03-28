package com.dasafio1.Consumindo_API.model;

import com.fasterxml.jackson.annotation.JsonProperty;
// DTO que representa os dados recebidos da API externa que estamos consultando.
public record PersonagemDTO(
        String name,
        String height,
        String mass,

        // Mapeia o "hair_color" do JSON para o "hairColor" no Java.
        @JsonProperty("hair_color")
        String hairColor
) {
}
