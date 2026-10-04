package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

public interface ClinicalRecordJpaRepository
        extends JpaRepository<ClinicalRecordJpaEntity, UUID> {

    boolean existsByRecordNumberIgnoreCase(String recordNumber);
}
