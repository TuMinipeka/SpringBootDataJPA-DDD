package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;

public interface MentalStatusExamJpaRepository
        extends JpaRepository<MentalStatusExamJpaEntity, UUID> {

    boolean existsByEncounterId(UUID encounterId);
}
