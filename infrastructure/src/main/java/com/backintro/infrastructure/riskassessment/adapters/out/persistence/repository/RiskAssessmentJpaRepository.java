package com.backintro.infrastructure.riskassessment.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

public interface RiskAssessmentJpaRepository
        extends JpaRepository<RiskAssessmentJpaEntity, UUID> {
}
