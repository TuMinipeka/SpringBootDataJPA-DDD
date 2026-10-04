package com.backintro.domain.messagetype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public record MessageTypeUpdatedEvent(
        MessageTypeId id,
        String nameType,
        LocalDateTime occurredOn
) implements DomainEvent {

    public MessageTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameType, "nameType must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
