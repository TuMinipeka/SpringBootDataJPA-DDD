package com.backintro.infrastructure.encountertype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

public interface EncounterTypeJpaRepository
        extends JpaRepository<EncounterTypeJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
