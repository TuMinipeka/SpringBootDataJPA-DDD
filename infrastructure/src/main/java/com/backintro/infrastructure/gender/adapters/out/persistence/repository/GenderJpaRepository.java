package com.backintro.infrastructure.gender.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;

public interface GenderJpaRepository
        extends JpaRepository<GenderJpaEntity, UUID> {

    boolean existsByDescriptionIgnoreCase(String description);
}
