package com.backintro.domain.citymunicipality.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public interface CityMunicipalityRepository {

    CityMunicipality save(CityMunicipality cityMunicipality);

    Optional<CityMunicipality> findById(CityMunicipalityId id);

    List<CityMunicipality> findAll();

    boolean existsByRegionIdAndCodeCity(
            StateRegionId regionId,
            String codeCity
    );

    void delete(CityMunicipality cityMunicipality);
}
