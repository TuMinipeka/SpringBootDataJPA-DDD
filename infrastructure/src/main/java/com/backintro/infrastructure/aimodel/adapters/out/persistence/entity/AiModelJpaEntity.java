package com.backintro.infrastructure.aimodel.adapters.out.persistence.entity;

import java.math.BigDecimal;
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
        name = "ai_models",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_ai_models_provider_key",
                columnNames = {"provider_model_id", "model_key"}
        )
)
public class AiModelJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "provider_model_id", nullable = false, updatable = false)
    private UUID providerModelId;

    @Column(name = "name_model", nullable = false, length = 100)
    private String nameModel;

    @Column(name = "model_key", nullable = false, length = 120)
    private String modelKey;

    @Column(
            name = "input_token_price",
            nullable = false,
            precision = 12,
            scale = 8
    )
    private BigDecimal inputTokenPrice;

    @Column(
            name = "output_token_price",
            nullable = false,
            precision = 12,
            scale = 8
    )
    private BigDecimal outputTokenPrice;

    @Column(name = "max_tokens")
    private Integer maxTokens;

    @Column(name = "context_window")
    private Integer contextWindow;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected AiModelJpaEntity() {
        // Required by JPA.
    }

    public AiModelJpaEntity(
            UUID id,
            UUID providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean active
    ) {
        this.id = id;
        this.providerModelId = providerModelId;
        this.nameModel = nameModel;
        this.modelKey = modelKey;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.active = active;
    }

    public void synchronize(
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean active
    ) {
        this.nameModel = nameModel;
        this.modelKey = modelKey;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.active = active;
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

    public UUID getProviderModelId() {
        return providerModelId;
    }

    public String getNameModel() {
        return nameModel;
    }

    public String getModelKey() {
        return modelKey;
    }

    public BigDecimal getInputTokenPrice() {
        return inputTokenPrice;
    }

    public BigDecimal getOutputTokenPrice() {
        return outputTokenPrice;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public Integer getContextWindow() {
        return contextWindow;
    }

    public boolean isActive() {
        return active;
    }
}
