package com.backintro.infrastructure.stateregion.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateStateRegionRequest(

        @NotNull(message = "countryId is required")
        UUID countryId,

        @NotBlank(message = "nameRegion is required")
        @Size(max = 50, message = "nameRegion must have at most 50 characters")
        String nameRegion,

        @NotBlank(message = "codeRegion is required")
        @Size(max = 10, message = "codeRegion must have at most 10 characters")
        String codeRegion

) {
}
