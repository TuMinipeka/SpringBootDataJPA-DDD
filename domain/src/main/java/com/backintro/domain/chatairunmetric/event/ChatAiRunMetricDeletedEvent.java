package com.backintro.domain.chatairunmetric.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunMetricDeletedEvent(
        ChatAiRunMetricId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunMetricDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
