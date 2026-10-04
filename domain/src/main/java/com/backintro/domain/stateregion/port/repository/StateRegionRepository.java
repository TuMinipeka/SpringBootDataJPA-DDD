package com.backintro.domain.stateregion.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public interface StateRegionRepository {

    StateRegion save(StateRegion stateRegion);

    Optional<StateRegion> findById(StateRegionId id);

    List<StateRegion> findAll();

    boolean existsByCountryIdAndCodeRegion(
            CountryId countryId,
            String codeRegion
    );

    void delete(StateRegion stateRegion);
}
