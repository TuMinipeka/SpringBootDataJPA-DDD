package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "chat_conversation_ai_settings",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_chat_ai_settings_conversation",
                columnNames = {"conversation_id"}
        )
)
public class ChatConversationAiSettingJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(
            name = "conversation_id",
            nullable = false,
            updatable = false
    )
    private UUID conversationId;

    @Column(name = "ai_enabled", nullable = false)
    private boolean aiEnabled;

    @Column(name = "default_model_id")
    private UUID defaultModelId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ChatConversationAiSettingJpaEntity() {
        // Required by JPA.
    }

    public ChatConversationAiSettingJpaEntity(
            UUID id,
            UUID conversationId,
            boolean aiEnabled,
            UUID defaultModelId
    ) {
        this.id = id;
        this.conversationId = conversationId;
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;
    }

    public void synchronize(
            boolean aiEnabled,
            UUID defaultModelId
    ) {
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;
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

    public boolean isAiEnabled() {
        return aiEnabled;
    }

    public UUID getDefaultModelId() {
        return defaultModelId;
    }
}
