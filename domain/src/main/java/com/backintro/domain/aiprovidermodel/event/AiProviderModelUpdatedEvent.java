package com.backintro.domain.aiprovidermodel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public record AiProviderModelUpdatedEvent(
        AiProviderModelId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        LocalDateTime occurredOn
) implements DomainEvent {

    public AiProviderModelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
