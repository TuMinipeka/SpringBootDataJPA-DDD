package com.backintro.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatEscalationStatusHistoryDeletedEvent(
        ChatEscalationStatusHistoryId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatEscalationStatusHistoryDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
