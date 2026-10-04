package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.entity.AiProviderModelJpaEntity;

public interface AiProviderModelJpaRepository
        extends JpaRepository<AiProviderModelJpaEntity, UUID> {

    boolean existsByNameProviderAiIgnoreCase(String nameProviderAi);
}
