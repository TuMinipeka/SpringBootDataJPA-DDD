package com.backintro.infrastructure.chatairun.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_ai_runs")
public class ChatAiRunJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(
            name = "conversation_id",
            nullable = false,
            updatable = false
    )
    private UUID conversationId;

    @Column(name = "message_id")
    private UUID messageId;

    @Column(name = "model_id", nullable = false)
    private UUID modelId;

    @Column(name = "ai_run_status_id", nullable = false)
    private UUID aiRunStatusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ChatAiRunJpaEntity() {
        // Required by JPA.
    }

    public ChatAiRunJpaEntity(
            UUID id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId
    ) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.modelId = modelId;
        this.aiRunStatusId = aiRunStatusId;
    }

    public void synchronize(
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId
    ) {
        this.messageId = messageId;
        this.modelId = modelId;
        this.aiRunStatusId = aiRunStatusId;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public UUID getModelId() {
        return modelId;
    }

    public UUID getAiRunStatusId() {
        return aiRunStatusId;
    }
}
