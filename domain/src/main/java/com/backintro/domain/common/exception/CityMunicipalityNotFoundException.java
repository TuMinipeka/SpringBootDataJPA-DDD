package com.backintro.domain.common.exception;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public class CityMunicipalityNotFoundException extends RuntimeException {

    public CityMunicipalityNotFoundException(CityMunicipalityId id) {
        super("City municipality not found with id: " + id.value());
    }
}
