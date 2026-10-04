package com.backintro.domain.conversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record ConversationStatusUpdatedEvent(
        ConversationStatusId id,
        String nameStatus,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ConversationStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
