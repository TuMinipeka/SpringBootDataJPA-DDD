package com.backintro.application.citymunicipality.usecase;

import com.backintro.application.citymunicipality.dto.CityMunicipalityResponse;
import com.backintro.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class GetCityMunicipalityByIdUseCase {

    private final CityMunicipalityRepository cityMunicipalityRepository;

    public GetCityMunicipalityByIdUseCase(
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        this.cityMunicipalityRepository = cityMunicipalityRepository;
    }

    public CityMunicipalityResponse execute(CityMunicipalityId id) {
        var cityMunicipality = cityMunicipalityRepository.findById(id)
                .orElseThrow(() ->
                        new CityMunicipalityNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new CityMunicipalityResponse(
                cityMunicipality.id().value(),
                cityMunicipality.nameCity(),
                cityMunicipality.codeCity()
        );
    }
}
