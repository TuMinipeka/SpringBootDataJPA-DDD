package com.backintro.domain.gender.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.gender.model.valueobject.GenderId;

public record GenderDeletedEvent(
        GenderId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public GenderDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
