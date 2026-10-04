package com.backintro.infrastructure.encounterstatus.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

public interface EncounterStatusJpaRepository
        extends JpaRepository<EncounterStatusJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
