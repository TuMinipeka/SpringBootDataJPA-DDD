package com.backintro.domain.sendertype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public record SenderTypeDeletedEvent(
        SenderTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public SenderTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
