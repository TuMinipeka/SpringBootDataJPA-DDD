package com.backintro.application.country.usecase;

import java.util.List;

import com.backintro.application.country.dto.CountryResponse;
import com.backintro.domain.country.port.repository.CountryRepository;

public class ListCountryUseCase {

    private final CountryRepository countryRepository;

    public ListCountryUseCase(
            CountryRepository countryRepository
    ) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponse> execute() {

        return countryRepository.findAll()
                .stream()
                .map(country ->
                        new CountryResponse(
                                country.id().value(),
                                country.name(),
                                country.code()
                        )
                )
                .toList();
    }
}
