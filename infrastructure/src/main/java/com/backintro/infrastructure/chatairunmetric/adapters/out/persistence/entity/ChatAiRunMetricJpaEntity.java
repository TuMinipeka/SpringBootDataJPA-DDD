package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.entity;

import java.math.BigDecimal;
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
        name = "chat_ai_run_metrics",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_chat_ai_run_metrics_run",
                columnNames = {"ai_run_id"}
        )
)
public class ChatAiRunMetricJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "ai_run_id", nullable = false, updatable = false)
    private UUID aiRunId;

    @Column(name = "prompt_tokens", nullable = false)
    private int promptTokens;

    @Column(name = "completion_tokens", nullable = false)
    private int completionTokens;

    @Column(name = "total_tokens", nullable = false)
    private int totalTokens;

    @Column(name = "cost", nullable = false, precision = 10, scale = 6)
    private BigDecimal cost;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected ChatAiRunMetricJpaEntity() {
        // Required by JPA.
    }

    public ChatAiRunMetricJpaEntity(
            UUID id,
            UUID aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        this.id = id;
        this.aiRunId = aiRunId;
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = cost;
    }

    public void synchronize(
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = cost;
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

    public int getPromptTokens() {
        return promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
