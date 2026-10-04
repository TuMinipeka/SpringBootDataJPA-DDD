package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

public interface ChatConversationAiSettingJpaRepository
        extends JpaRepository<ChatConversationAiSettingJpaEntity, UUID> {

    boolean existsByConversationId(UUID conversationId);
}
