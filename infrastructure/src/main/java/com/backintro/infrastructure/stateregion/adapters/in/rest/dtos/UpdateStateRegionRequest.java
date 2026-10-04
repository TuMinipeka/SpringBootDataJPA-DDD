package com.backintro.infrastructure.stateregion.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateStateRegionRequest(

        @NotBlank(message = "nameRegion is required")
        @Size(max = 50, message = "nameRegion must have at most 50 characters")
        String nameRegion,

        @NotBlank(message = "codeRegion is required")
        @Size(max = 10, message = "codeRegion must have at most 10 characters")
        String codeRegion

) {
}
