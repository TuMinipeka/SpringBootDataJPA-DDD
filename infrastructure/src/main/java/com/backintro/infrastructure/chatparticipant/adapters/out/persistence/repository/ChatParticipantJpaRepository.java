package com.backintro.infrastructure.chatparticipant.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

public interface ChatParticipantJpaRepository
        extends JpaRepository<ChatParticipantJpaEntity, UUID> {
}
