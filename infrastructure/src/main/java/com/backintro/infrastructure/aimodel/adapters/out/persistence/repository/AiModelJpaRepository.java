package com.backintro.infrastructure.aimodel.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;

public interface AiModelJpaRepository
        extends JpaRepository<AiModelJpaEntity, UUID> {

    boolean existsByProviderModelIdAndModelKeyIgnoreCase(
            UUID providerModelId,
            String modelKey
    );
}
