package com.backintro.application.contact.command;

import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record RegisterContactCommand(
        String fullName,
        String email,
        String notes,
        CityMunicipalityId cityId
) {

    public RegisterContactCommand {
        Objects.requireNonNull(fullName, "fullName must not be null");
    }
}
