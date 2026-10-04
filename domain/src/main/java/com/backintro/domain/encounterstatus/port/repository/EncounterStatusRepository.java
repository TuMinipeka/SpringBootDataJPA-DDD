package com.backintro.domain.encounterstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public interface EncounterStatusRepository {

    EncounterStatus save(EncounterStatus encounterStatus);

    Optional<EncounterStatus> findById(EncounterStatusId id);

    List<EncounterStatus> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(EncounterStatus encounterStatus);
}
