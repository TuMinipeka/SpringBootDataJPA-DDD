package com.backintro.infrastructure.professional.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfessionalRequest(

        @NotBlank(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotBlank(message = "firstName is required")
        @Size(max = 60, message = "firstName must have at most 60 characters")
        String firstName,

        @NotBlank(message = "lastName is required")
        @Size(max = 60, message = "lastName must have at most 60 characters")
        String lastName,

        @Size(max = 100, message = "licenseNumber must have at most 100 characters")
        String licenseNumber

) {
}
