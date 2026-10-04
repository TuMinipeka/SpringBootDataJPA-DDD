package com.backintro.application.professional.command;

import java.util.Objects;

import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record UpdateProfessionalCommand(
        ProfessionalId id,
        String documentNumber,
        String firstName,
        String lastName,
        String licenseNumber
) {

    public UpdateProfessionalCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
    }
}
