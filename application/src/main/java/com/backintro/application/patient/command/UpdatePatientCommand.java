package com.backintro.application.patient.command;

import java.time.LocalDate;
import java.util.Objects;

import com.backintro.domain.patient.model.valueobject.PatientId;

public record UpdatePatientCommand(
        PatientId id,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        String email,
        String phone,
        String address
) {

    public UpdatePatientCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(birthDate, "birthDate must not be null");
    }
}
