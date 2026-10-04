package com.backintro.application.patientallergy.command;

import java.util.Objects;

import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;

public record UpdatePatientAllergyCommand(
        PatientAllergyId id,
        String substance,
        String reaction,
        String severity,
        boolean active
) {

    public UpdatePatientAllergyCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(substance, "substance must not be null");
    }
}
