package com.backintro.infrastructure.professionalstudy.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProfessionalStudyRequest(

        @NotNull(message = "studyId is required")
        UUID studyId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotBlank(message = "title is required")
        @Size(max = 100, message = "title must have at most 100 characters")
        String title,

        @Size(max = 100, message = "university must have at most 100 characters")
        String university,

        @Size(max = 60, message = "resolutionNumber must have at most 60 characters")
        String resolutionNumber,

        UUID countryId

) {
}
