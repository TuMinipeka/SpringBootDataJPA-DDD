package com.backintro.domain.gender.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.gender.model.valueobject.GenderId;

public record GenderUpdatedEvent(
        GenderId id,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {

    public GenderUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
