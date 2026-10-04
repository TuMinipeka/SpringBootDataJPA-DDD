package com.backintro.application.citymunicipality.usecase;

import java.time.LocalDateTime;

import com.backintro.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.backintro.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class DeleteCityMunicipalityUseCase {

    private final CityMunicipalityRepository cityMunicipalityRepository;

    public DeleteCityMunicipalityUseCase(
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        this.cityMunicipalityRepository = cityMunicipalityRepository;
    }

    public CityMunicipalityDeletedEvent execute(CityMunicipalityId id) {
        var cityMunicipality = cityMunicipalityRepository.findById(id)
                .orElseThrow(() ->
                        new CityMunicipalityNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        cityMunicipalityRepository.delete(cityMunicipality);

        return new CityMunicipalityDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
