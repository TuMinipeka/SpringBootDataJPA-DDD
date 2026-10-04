package com.backintro.application.country.usecase;

import com.backintro.application.country.dto.CountryResponse;
import com.backintro.application.country.exception.CountryNotFoundApplicationException;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.country.port.repository.CountryRepository;

public class GetCountryByIdUseCase {

    private final CountryRepository countryRepository;

    public GetCountryByIdUseCase(
            CountryRepository countryRepository
    ) {
        this.countryRepository = countryRepository;
    }

    public CountryResponse execute(
            CountryId id
    ) {

        var country =
                countryRepository.findById(id)
                        .orElseThrow(() ->
                                new CountryNotFoundApplicationException(
                                        id.value().toString()
                                )
                        );

        return new CountryResponse(
                country.id().value(),
                country.name(),
                country.code()
        );
    }
}
