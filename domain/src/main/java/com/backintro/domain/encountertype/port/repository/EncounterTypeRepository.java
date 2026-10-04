package com.backintro.domain.encountertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.encountertype.model.aggregate.EncounterType;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public interface EncounterTypeRepository {

    EncounterType save(EncounterType encounterType);

    Optional<EncounterType> findById(EncounterTypeId id);

    List<EncounterType> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(EncounterType encounterType);
}
