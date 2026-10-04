package com.backintro.domain.aimodel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;

public record AiModelRegisteredEvent(
        AiModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public AiModelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
