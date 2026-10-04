package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "chat_escalation_assignments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_chat_escalation_assignments",
                columnNames = {"escalation_id", "professional_id"}
        )
)
public class ChatEscalationAssignmentJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "escalation_id", nullable = false, updatable = false)
    private UUID escalationId;

    @Column(name = "professional_id", nullable = false)
    private UUID professionalId;

    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    protected ChatEscalationAssignmentJpaEntity() {
        // Required by JPA.
    }

    public ChatEscalationAssignmentJpaEntity(
            UUID id,
            UUID escalationId,
            UUID professionalId
    ) {
        this.id = id;
        this.escalationId = escalationId;
        this.professionalId = professionalId;
    }

    public void synchronize(UUID professionalId) {
        this.professionalId = professionalId;
    }

    @PrePersist
    void onCreate() {
        if (assignedAt == null) {
            assignedAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getEscalationId() {
        return escalationId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }
}
