package com.backintro.application.stateregion.usecase;

import com.backintro.application.country.exception.CountryNotFoundApplicationException;
import com.backintro.application.stateregion.command.RegisterStateRegionCommand;
import com.backintro.application.stateregion.dto.StateRegionResponse;
import com.backintro.domain.country.port.repository.CountryRepository;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class RegisterStateRegionUseCase {

    private final StateRegionRepository stateRegionRepository;
    private final CountryRepository countryRepository;

    public RegisterStateRegionUseCase(
            StateRegionRepository stateRegionRepository,
            CountryRepository countryRepository
    ) {
        this.stateRegionRepository = stateRegionRepository;
        this.countryRepository = countryRepository;
    }

    public StateRegionResponse execute(RegisterStateRegionCommand command) {
        countryRepository.findById(command.countryId())
                .orElseThrow(() ->
                        new CountryNotFoundApplicationException(
                                command.countryId().value().toString()
                        )
                );

        StateRegion stateRegion = StateRegion.register(
                command.countryId(),
                command.nameRegion(),
                command.codeRegion()
        );

        StateRegion saved = stateRegionRepository.save(stateRegion);

        return new StateRegionResponse(
                saved.id().value(),
                saved.nameRegion(),
                saved.codeRegion()
        );
    }
}
