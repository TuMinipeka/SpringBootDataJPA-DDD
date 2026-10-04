package com.backintro.domain.country.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.country.model.aggregate.Country;
import com.backintro.domain.country.model.valueobject.CountryId;


public interface CountryRepository {
    Country save(Country country);

    Optional<Country> findById(CountryId id);

    List<Country> findAll();

    boolean existsByCode(String code);

    void delete(Country country);
}
