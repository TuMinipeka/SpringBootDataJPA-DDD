package com.backintro.application.country.usecase;

import com.backintro.application.country.command.UpdateCountryCommand;
import com.backintro.application.country.dto.CountryResponse;
import com.backintro.application.country.exception.CountryNotFoundApplicationException;
import com.backintro.domain.country.port.repository.CountryRepository;

public class UpdateCountryUseCase {

    private final CountryRepository countryRepository;

    public UpdateCountryUseCase(
            CountryRepository countryRepository
    ) {
        this.countryRepository = countryRepository;
    }

    public CountryResponse execute(
            UpdateCountryCommand command
    ) {

        var country =
                countryRepository.findById(command.id())
                        .orElseThrow(() ->
                                new CountryNotFoundApplicationException(
                                        command.id()
                                                .value()
                                                .toString()
                                )
                        );

        country.update(
                command.name(),
                command.code()
        );

        var updated =
                countryRepository.save(country);

        return new CountryResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
