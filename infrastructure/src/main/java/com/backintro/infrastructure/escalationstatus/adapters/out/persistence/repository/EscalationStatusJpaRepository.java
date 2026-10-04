package com.backintro.infrastructure.escalationstatus.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

public interface EscalationStatusJpaRepository
        extends JpaRepository<EscalationStatusJpaEntity, UUID> {

    boolean existsByNameStatusIgnoreCase(String nameStatus);
}
