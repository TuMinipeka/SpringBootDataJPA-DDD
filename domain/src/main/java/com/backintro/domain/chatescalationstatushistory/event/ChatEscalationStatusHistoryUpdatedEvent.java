package com.backintro.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record ChatEscalationStatusHistoryUpdatedEvent(
        ChatEscalationStatusHistoryId id,
        EscalationStatusId escalationStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatEscalationStatusHistoryUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                escalationStatusId,
                "escalationStatusId must not be null"
        );
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
