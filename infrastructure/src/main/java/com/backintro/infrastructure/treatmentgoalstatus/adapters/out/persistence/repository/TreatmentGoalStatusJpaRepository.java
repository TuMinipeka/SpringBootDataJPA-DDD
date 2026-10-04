package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

public interface TreatmentGoalStatusJpaRepository
        extends JpaRepository<TreatmentGoalStatusJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
