package com.backintro.infrastructure.assessmenttype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

public interface AssessmentTypeJpaRepository
        extends JpaRepository<AssessmentTypeJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
