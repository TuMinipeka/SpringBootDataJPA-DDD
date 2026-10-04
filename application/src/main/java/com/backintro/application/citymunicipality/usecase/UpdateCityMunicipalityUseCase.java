package com.backintro.application.citymunicipality.usecase;

import com.backintro.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import com.backintro.application.citymunicipality.dto.CityMunicipalityResponse;
import com.backintro.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class UpdateCityMunicipalityUseCase {

    private final CityMunicipalityRepository cityMunicipalityRepository;

    public UpdateCityMunicipalityUseCase(
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        this.cityMunicipalityRepository = cityMunicipalityRepository;
    }

    public CityMunicipalityResponse execute(
            UpdateCityMunicipalityCommand command
    ) {
        var cityMunicipality = cityMunicipalityRepository.findById(command.id())
                .orElseThrow(() ->
                        new CityMunicipalityNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        cityMunicipality.update(
                command.nameCity(),
                command.codeCity()
        );

        var updated = cityMunicipalityRepository.save(cityMunicipality);

        return new CityMunicipalityResponse(
                updated.id().value(),
                updated.nameCity(),
                updated.codeCity()
        );
    }
}
