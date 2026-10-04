package com.backintro.application.citymunicipality.command;

import java.util.Objects;

import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public record RegisterCityMunicipalityCommand(
        StateRegionId regionId,
        String nameCity,
        String codeCity
) {

    public RegisterCityMunicipalityCommand {
        Objects.requireNonNull(regionId, "regionId must not be null");
        Objects.requireNonNull(nameCity, "nameCity must not be null");
        Objects.requireNonNull(codeCity, "codeCity must not be null");
    }
}
