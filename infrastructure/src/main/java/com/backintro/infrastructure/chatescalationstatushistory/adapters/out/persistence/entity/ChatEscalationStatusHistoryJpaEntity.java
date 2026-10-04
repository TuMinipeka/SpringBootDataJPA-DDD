package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_escalation_status_history")
public class ChatEscalationStatusHistoryJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "escalation_id", nullable = false, updatable = false)
    private UUID escalationId;

    @Column(name = "escalation_status_id", nullable = false)
    private UUID escalationStatusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    protected ChatEscalationStatusHistoryJpaEntity() {
        // Required by JPA.
    }

    public ChatEscalationStatusHistoryJpaEntity(
            UUID id,
            UUID escalationId,
            UUID escalationStatusId
    ) {
        this.id = id;
        this.escalationId = escalationId;
        this.escalationStatusId = escalationStatusId;
    }

    public void synchronize(UUID escalationStatusId) {
        this.escalationStatusId = escalationStatusId;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        if (changedAt == null) {
            changedAt = now;
        }
    }

    @PreUpdate
    void onUpdate() {
        changedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getEscalationId() {
        return escalationId;
    }

    public UUID getEscalationStatusId() {
        return escalationStatusId;
    }
}
