package com.backintro.domain.conversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record ConversationStatusDeletedEvent(
        ConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ConversationStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
