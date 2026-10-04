package com.backintro.infrastructure.professionaltype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

public interface ProfessionalTypeJpaRepository
        extends JpaRepository<ProfessionalTypeJpaEntity, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
