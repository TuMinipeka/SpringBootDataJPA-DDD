package com.backintro.infrastructure.encountermodality.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

public interface EncounterModalityJpaRepository
        extends JpaRepository<EncounterModalityJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
