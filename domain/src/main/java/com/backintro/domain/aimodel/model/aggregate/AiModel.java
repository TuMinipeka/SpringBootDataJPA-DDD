package com.backintro.domain.aimodel.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.event.AiModelRegisteredEvent;
import com.backintro.domain.aimodel.event.AiModelUpdatedEvent;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.domain.common.model.AggregateRoot;

public class AiModel extends AggregateRoot {

    private final AiModelId id;
    private final AiProviderModelId providerModelId;
    private String nameModel;
    private String modelKey;
    private BigDecimal inputTokenPrice;
    private BigDecimal outputTokenPrice;
    private Integer maxTokens;
    private Integer contextWindow;
    private boolean active;

    private AiModel(
            AiModelId id,
            AiProviderModelId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.providerModelId = Objects.requireNonNull(
                providerModelId,
                "providerModelId must not be null"
        );
        this.nameModel = Objects.requireNonNull(
                nameModel,
                "nameModel must not be null"
        );
        this.modelKey = Objects.requireNonNull(
                modelKey,
                "modelKey must not be null"
        );
        this.inputTokenPrice = requireNonNegativePrice(
                inputTokenPrice,
                "inputTokenPrice"
        );
        this.outputTokenPrice = requireNonNegativePrice(
                outputTokenPrice,
                "outputTokenPrice"
        );
        this.maxTokens = requireNonNegativeValue(maxTokens, "maxTokens");
        this.contextWindow = requireNonNegativeValue(
                contextWindow,
                "contextWindow"
        );
        this.active = active;
    }

    public static AiModel register(
            AiProviderModelId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow
    ) {
        AiModelId id = AiModelId.generate();
        AiModel aiModel = new AiModel(
                id,
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                true
        );

        aiModel.recordEvent(
                new AiModelRegisteredEvent(id, LocalDateTime.now())
        );

        return aiModel;
    }

    public static AiModel restore(
            AiModelId id,
            AiProviderModelId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean active
    ) {
        return new AiModel(
                id,
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                active
        );
    }

    public void update(
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow
    ) {
        this.nameModel = Objects.requireNonNull(
                nameModel,
                "nameModel must not be null"
        );
        this.modelKey = Objects.requireNonNull(
                modelKey,
                "modelKey must not be null"
        );
        this.inputTokenPrice = requireNonNegativePrice(
                inputTokenPrice,
                "inputTokenPrice"
        );
        this.outputTokenPrice = requireNonNegativePrice(
                outputTokenPrice,
                "outputTokenPrice"
        );
        this.maxTokens = requireNonNegativeValue(maxTokens, "maxTokens");
        this.contextWindow = requireNonNegativeValue(
                contextWindow,
                "contextWindow"
        );

        recordEvent(
                new AiModelUpdatedEvent(
                        this.id,
                        this.nameModel,
                        this.modelKey,
                        this.inputTokenPrice,
                        this.outputTokenPrice,
                        this.maxTokens,
                        this.contextWindow,
                        LocalDateTime.now()
                )
        );
    }

    private static BigDecimal requireNonNegativePrice(
            BigDecimal value,
            String fieldName
    ) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.signum() < 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be greater than or equal to zero"
            );
        }
        return value;
    }

    private static Integer requireNonNegativeValue(
            Integer value,
            String fieldName
    ) {
        if (value != null && value < 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be greater than or equal to zero"
            );
        }
        return value;
    }

    public AiModelId id() {
        return id;
    }

    public AiProviderModelId providerModelId() {
        return providerModelId;
    }

    public String nameModel() {
        return nameModel;
    }

    public String modelKey() {
        return modelKey;
    }

    public BigDecimal inputTokenPrice() {
        return inputTokenPrice;
    }

    public BigDecimal outputTokenPrice() {
        return outputTokenPrice;
    }

    public Integer maxTokens() {
        return maxTokens;
    }

    public Integer contextWindow() {
        return contextWindow;
    }

    public boolean active() {
        return active;
    }
}
