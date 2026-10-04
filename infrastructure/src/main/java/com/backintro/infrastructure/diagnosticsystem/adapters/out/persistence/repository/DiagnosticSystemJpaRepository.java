package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

public interface DiagnosticSystemJpaRepository
        extends JpaRepository<DiagnosticSystemJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCaseAndVersionIgnoreCase(
            String name,
            String version
    );
}
