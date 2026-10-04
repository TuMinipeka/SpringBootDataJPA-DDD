package com.backintro.domain.chatairunmetric.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunMetricUpdatedEvent(
        ChatAiRunMetricId id,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        BigDecimal cost,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunMetricUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(cost, "cost must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
