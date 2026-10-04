package com.backintro.application.stateregion.command;

import java.util.Objects;

import com.backintro.domain.country.model.valueobject.CountryId;

public record RegisterStateRegionCommand(
        CountryId countryId,
        String nameRegion,
        String codeRegion
) {

    public RegisterStateRegionCommand {
        Objects.requireNonNull(countryId, "countryId must not be null");
        Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        Objects.requireNonNull(codeRegion, "codeRegion must not be null");
    }
}
