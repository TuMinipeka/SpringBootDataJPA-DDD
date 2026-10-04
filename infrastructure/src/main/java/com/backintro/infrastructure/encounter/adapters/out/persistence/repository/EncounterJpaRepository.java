package com.backintro.infrastructure.encounter.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

public interface EncounterJpaRepository
        extends JpaRepository<EncounterJpaEntity, UUID> {
}
