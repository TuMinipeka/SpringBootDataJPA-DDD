package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

public interface ChatEscalationStatusHistoryJpaRepository
        extends JpaRepository<ChatEscalationStatusHistoryJpaEntity, UUID> {
}
