package com.backintro.application.country.usecase;

import java.time.LocalDateTime;

import com.backintro.application.country.exception.CountryNotFoundApplicationException;
import com.backintro.domain.country.event.CountryDeletedEvent;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.country.port.repository.CountryRepository;

public class DeleteCountryUseCase {

    private final CountryRepository countryRepository;

    public DeleteCountryUseCase(
            CountryRepository countryRepository
    ) {
        this.countryRepository = countryRepository;
    }

    public CountryDeletedEvent execute(
            CountryId id
    ) {

        var country =
                countryRepository.findById(id)
                        .orElseThrow(() ->
                                new CountryNotFoundApplicationException(
                                        id.value().toString()
                                )
                        );

        countryRepository.delete(country);

        return new CountryDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
