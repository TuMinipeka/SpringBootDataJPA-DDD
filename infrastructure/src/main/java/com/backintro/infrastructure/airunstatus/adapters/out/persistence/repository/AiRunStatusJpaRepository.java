package com.backintro.infrastructure.airunstatus.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;

public interface AiRunStatusJpaRepository
        extends JpaRepository<AiRunStatusJpaEntity, UUID> {

    boolean existsByNameStatusIgnoreCase(String nameStatus);
}
