package com.backintro.infrastructure.chatairunerror.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

public interface ChatAiRunErrorJpaRepository
        extends JpaRepository<ChatAiRunErrorJpaEntity, UUID> {
}
