package com.backintro.infrastructure.study.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;

public interface StudyJpaRepository
        extends JpaRepository<StudyJpaEntity, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
