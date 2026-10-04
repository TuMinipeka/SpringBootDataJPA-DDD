package com.backintro.domain.aimodel.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.common.event.DomainEvent;

public record AiModelUpdatedEvent(
        AiModelId id,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow,
        LocalDateTime occurredOn
) implements DomainEvent {

    public AiModelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameModel, "nameModel must not be null");
        Objects.requireNonNull(modelKey, "modelKey must not be null");
        Objects.requireNonNull(
                inputTokenPrice,
                "inputTokenPrice must not be null"
        );
        Objects.requireNonNull(
                outputTokenPrice,
                "outputTokenPrice must not be null"
        );
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
