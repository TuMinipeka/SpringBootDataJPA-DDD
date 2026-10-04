package com.backintro.infrastructure.chatconversation.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

public interface ChatConversationJpaRepository
        extends JpaRepository<ChatConversationJpaEntity, UUID> {
}
