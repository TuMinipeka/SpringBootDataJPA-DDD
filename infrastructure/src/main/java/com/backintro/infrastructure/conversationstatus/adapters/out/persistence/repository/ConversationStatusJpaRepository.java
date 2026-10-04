package com.backintro.infrastructure.conversationstatus.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

public interface ConversationStatusJpaRepository
        extends JpaRepository<ConversationStatusJpaEntity, UUID> {

    boolean existsByNameStatusIgnoreCase(String nameStatus);
}
