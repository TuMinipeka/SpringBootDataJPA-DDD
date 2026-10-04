package com.backintro.infrastructure.chatescalation.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_escalations")
public class ChatEscalationJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(
            name = "conversation_id",
            nullable = false,
            updatable = false
    )
    private UUID conversationId;

    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    @Column(name = "from_ai", nullable = false)
    private boolean fromAi;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected ChatEscalationJpaEntity() {
        // Required by JPA.
    }

    public ChatEscalationJpaEntity(
            UUID id,
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason
    ) {
        this.id = id;
        this.conversationId = conversationId;
        this.statusId = statusId;
        this.fromAi = fromAi;
        this.reason = reason;
    }

    public void synchronize(
            UUID statusId,
            boolean fromAi,
            String reason
    ) {
        this.statusId = statusId;
        this.fromAi = fromAi;
        this.reason = reason;
    }

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public boolean isFromAi() {
        return fromAi;
    }

    public String getReason() {
        return reason;
    }
}
