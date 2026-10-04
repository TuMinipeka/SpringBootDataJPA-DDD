package com.backintro.application.patientallergy.command;

import java.util.Objects;

import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record RegisterPatientAllergyCommand(
        PatientId patientId,
        String substance,
        String reaction,
        String severity,
        ProfessionalId recordedBy
) {

    public RegisterPatientAllergyCommand {
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(substance, "substance must not be null");
    }
}
