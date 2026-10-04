package com.backintro.infrastructure.chatmessage.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

public interface ChatMessageJpaRepository
        extends JpaRepository<ChatMessageJpaEntity, UUID> {
}
