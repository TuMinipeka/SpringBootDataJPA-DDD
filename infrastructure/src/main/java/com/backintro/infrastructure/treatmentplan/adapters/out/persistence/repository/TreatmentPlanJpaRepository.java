package com.backintro.infrastructure.treatmentplan.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

public interface TreatmentPlanJpaRepository
        extends JpaRepository<TreatmentPlanJpaEntity, UUID> {
}
