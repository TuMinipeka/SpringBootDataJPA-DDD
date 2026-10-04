package com.backintro.application.patientcontact.command;

import java.util.Objects;

import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;

public record UpdatePatientContactCommand(
        PatientContactId id,
        boolean primaryContact,
        boolean emergencyContact
) {

    public UpdatePatientContactCommand {
        Objects.requireNonNull(id, "id must not be null");
    }
}
