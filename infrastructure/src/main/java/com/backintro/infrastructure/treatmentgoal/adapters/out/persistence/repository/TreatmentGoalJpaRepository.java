package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

public interface TreatmentGoalJpaRepository
        extends JpaRepository<TreatmentGoalJpaEntity, UUID> {
}
