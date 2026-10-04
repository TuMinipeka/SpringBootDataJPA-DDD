package com.backintro.domain.consenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.common.event.DomainEvent;

public record ConsentTypeUpdatedEvent(
        ConsentTypeId id,
        String name,
        String code,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ConsentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
