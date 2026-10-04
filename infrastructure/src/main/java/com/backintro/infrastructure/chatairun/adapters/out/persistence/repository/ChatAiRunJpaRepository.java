package com.backintro.infrastructure.chatairun.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

public interface ChatAiRunJpaRepository
        extends JpaRepository<ChatAiRunJpaEntity, UUID> {
}
