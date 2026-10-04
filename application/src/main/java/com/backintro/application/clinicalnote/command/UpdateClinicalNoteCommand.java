package com.backintro.application.clinicalnote.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public record UpdateClinicalNoteCommand(
        ClinicalNoteId id,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt
) {

    public UpdateClinicalNoteCommand {
        Objects.requireNonNull(id, "id must not be null");
    }
}
