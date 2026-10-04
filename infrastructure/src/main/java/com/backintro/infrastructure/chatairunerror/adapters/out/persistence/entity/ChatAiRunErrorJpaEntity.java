package com.backintro.infrastructure.chatairunerror.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_ai_run_errors")
public class ChatAiRunErrorJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "ai_run_id", nullable = false, updatable = false)
    private UUID aiRunId;

    @Column(name = "error_message", nullable = false, columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "error_code", length = 30)
    private String errorCode;

    @Column(name = "provider_error_id", length = 120)
    private String providerErrorId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected ChatAiRunErrorJpaEntity() {
        // Required by JPA.
    }

    public ChatAiRunErrorJpaEntity(
            UUID id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        this.id = id;
        this.aiRunId = aiRunId;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
    }

    public void synchronize(
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
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

    public UUID getAiRunId() {
        return aiRunId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getProviderErrorId() {
        return providerErrorId;
    }
}
