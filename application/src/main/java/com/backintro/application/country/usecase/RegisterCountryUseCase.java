package com.backintro.application.country.usecase;

import com.backintro.application.country.command.RegisterCountryCommand;
import com.backintro.application.country.dto.CountryResponse;
import com.backintro.domain.country.model.aggregate.Country;
import com.backintro.domain.country.port.repository.CountryRepository;

public class RegisterCountryUseCase {

    private final CountryRepository countryRepository;

    public RegisterCountryUseCase(
            CountryRepository countryRepository
    ) {
        this.countryRepository = countryRepository;
    }

    public CountryResponse execute(
            RegisterCountryCommand command
    ) {

        Country country = Country.register(
                command.name(),
                command.code()
        );

        Country saved =
                countryRepository.save(country);

        return new CountryResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
