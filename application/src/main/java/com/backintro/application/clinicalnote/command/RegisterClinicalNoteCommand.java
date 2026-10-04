package com.backintro.application.clinicalnote.command;

import java.util.Objects;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record RegisterClinicalNoteCommand(
        EncounterId encounterId,
        ProfessionalId professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes
) {

    public RegisterClinicalNoteCommand {
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
    }
}
