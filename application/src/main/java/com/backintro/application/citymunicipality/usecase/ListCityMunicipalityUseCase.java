package com.backintro.application.citymunicipality.usecase;

import java.util.List;

import com.backintro.application.citymunicipality.dto.CityMunicipalityResponse;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class ListCityMunicipalityUseCase {

    private final CityMunicipalityRepository cityMunicipalityRepository;

    public ListCityMunicipalityUseCase(
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        this.cityMunicipalityRepository = cityMunicipalityRepository;
    }

    public List<CityMunicipalityResponse> execute() {
        return cityMunicipalityRepository.findAll()
                .stream()
                .map(cityMunicipality ->
                        new CityMunicipalityResponse(
                                cityMunicipality.id().value(),
                                cityMunicipality.nameCity(),
                                cityMunicipality.codeCity()
                        )
                )
                .toList();
    }
}
