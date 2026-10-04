package com.backintro.domain.aiprovidermodel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public record AiProviderModelDeletedEvent(
        AiProviderModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public AiProviderModelDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
