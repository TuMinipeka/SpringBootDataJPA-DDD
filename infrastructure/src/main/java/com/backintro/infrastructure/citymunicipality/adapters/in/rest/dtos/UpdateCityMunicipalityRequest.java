package com.backintro.infrastructure.citymunicipality.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCityMunicipalityRequest(

        @NotBlank(message = "nameCity is required")
        @Size(max = 50, message = "nameCity must have at most 50 characters")
        String nameCity,

        @NotBlank(message = "codeCity is required")
        @Size(max = 10, message = "codeCity must have at most 10 characters")
        String codeCity

) {
}
