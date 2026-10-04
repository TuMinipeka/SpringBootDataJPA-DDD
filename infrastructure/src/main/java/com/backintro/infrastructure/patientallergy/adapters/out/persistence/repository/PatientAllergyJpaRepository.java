package com.backintro.infrastructure.patientallergy.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

public interface PatientAllergyJpaRepository
        extends JpaRepository<PatientAllergyJpaEntity, UUID> {
}
