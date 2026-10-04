package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

public interface ChatEscalationAssignmentJpaRepository
        extends JpaRepository<ChatEscalationAssignmentJpaEntity, UUID> {

    boolean existsByEscalationIdAndProfessionalId(
            UUID escalationId,
            UUID professionalId
    );
}
