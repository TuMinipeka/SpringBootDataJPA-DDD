package com.backintro.infrastructure.professionalstudy.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfessionalStudyRequest(

        @NotBlank(message = "title is required")
        @Size(max = 100, message = "title must have at most 100 characters")
        String title,

        @Size(max = 100, message = "university must have at most 100 characters")
        String university,

        boolean valid,

        @Size(max = 60, message = "resolutionNumber must have at most 60 characters")
        String resolutionNumber

) {
}
