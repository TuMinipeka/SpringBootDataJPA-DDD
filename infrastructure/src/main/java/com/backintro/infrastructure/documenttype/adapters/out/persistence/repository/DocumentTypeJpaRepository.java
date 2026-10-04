package com.backintro.infrastructure.documenttype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

public interface DocumentTypeJpaRepository
        extends JpaRepository<DocumentTypeJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);
}
