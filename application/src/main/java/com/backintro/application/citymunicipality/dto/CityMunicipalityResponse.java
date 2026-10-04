package com.backintro.application.citymunicipality.dto;

import java.util.UUID;

public record CityMunicipalityResponse(
        UUID id,
        String nameCity,
        String codeCity
) {
}
