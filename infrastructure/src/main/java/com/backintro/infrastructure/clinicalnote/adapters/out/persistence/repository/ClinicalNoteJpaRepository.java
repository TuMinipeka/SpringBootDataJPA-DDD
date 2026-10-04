package com.backintro.infrastructure.clinicalnote.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

public interface ClinicalNoteJpaRepository
        extends JpaRepository<ClinicalNoteJpaEntity, UUID> {
}
