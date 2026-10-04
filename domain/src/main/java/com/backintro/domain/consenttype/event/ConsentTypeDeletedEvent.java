package com.backintro.domain.consenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.common.event.DomainEvent;

public record ConsentTypeDeletedEvent(
        ConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ConsentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
