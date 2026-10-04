package com.backintro.domain.encounter.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encounter.model.valueobject.EncounterId;

public interface EncounterRepository {

    Encounter save(Encounter encounter);

    Optional<Encounter> findById(EncounterId id);

    List<Encounter> findAll();

    void delete(Encounter encounter);
}
