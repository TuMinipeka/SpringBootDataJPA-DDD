package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

public interface ChatAiRunMetricJpaRepository
        extends JpaRepository<ChatAiRunMetricJpaEntity, UUID> {

    boolean existsByAiRunId(UUID aiRunId);
}
