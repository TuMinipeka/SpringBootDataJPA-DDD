package com.backintro.application.professional.command;

import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record RegisterProfessionalCommand(
        DocumentTypeId documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        ProfessionalTypeId professionalTypeId,
        String licenseNumber,
        CityMunicipalityId cityId,
        ContactId contactId
) {

    public RegisterProfessionalCommand {
        Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(
                professionalTypeId,
                "professionalTypeId must not be null"
        );
    }
}
