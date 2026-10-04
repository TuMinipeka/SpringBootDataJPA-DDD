package com.backintro.application.citymunicipality.usecase;

import com.backintro.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import com.backintro.application.citymunicipality.dto.CityMunicipalityResponse;
import com.backintro.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class RegisterCityMunicipalityUseCase {

    private final CityMunicipalityRepository cityMunicipalityRepository;
    private final StateRegionRepository stateRegionRepository;

    public RegisterCityMunicipalityUseCase(
            CityMunicipalityRepository cityMunicipalityRepository,
            StateRegionRepository stateRegionRepository
    ) {
        this.cityMunicipalityRepository = cityMunicipalityRepository;
        this.stateRegionRepository = stateRegionRepository;
    }

    public CityMunicipalityResponse execute(
            RegisterCityMunicipalityCommand command
    ) {
        stateRegionRepository.findById(command.regionId())
                .orElseThrow(() ->
                        new StateRegionNotFoundApplicationException(
                                command.regionId().value().toString()
                        )
                );

        CityMunicipality cityMunicipality = CityMunicipality.register(
                command.regionId(),
                command.nameCity(),
                command.codeCity()
        );

        CityMunicipality saved =
                cityMunicipalityRepository.save(cityMunicipality);

        return new CityMunicipalityResponse(
                saved.id().value(),
                saved.nameCity(),
                saved.codeCity()
        );
    }
}
