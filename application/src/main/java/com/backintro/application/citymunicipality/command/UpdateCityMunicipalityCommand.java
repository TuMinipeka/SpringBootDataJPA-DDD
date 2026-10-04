package com.backintro.application.citymunicipality.command;

import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record UpdateCityMunicipalityCommand(
        CityMunicipalityId id,
        String nameCity,
        String codeCity
) {

    public UpdateCityMunicipalityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCity, "nameCity must not be null");
        Objects.requireNonNull(codeCity, "codeCity must not be null");
    }
}
