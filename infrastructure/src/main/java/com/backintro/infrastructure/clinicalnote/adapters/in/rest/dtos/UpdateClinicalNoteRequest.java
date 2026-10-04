package com.backintro.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;

public record UpdateClinicalNoteRequest(

        String subjective,

        String objective,

        String assessment,

        String plan,

        String additionalNotes,

        LocalDateTime signedAt

) {
}
