package com.backintro.infrastructure.consenttype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

public interface ConsentTypeJpaRepository
        extends JpaRepository<ConsentTypeJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
