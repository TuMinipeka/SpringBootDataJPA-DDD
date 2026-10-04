package com.backintro.domain.chatescalation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record ChatEscalationUpdatedEvent(
        ChatEscalationId id,
        EscalationStatusId statusId,
        boolean fromAi,
        String reason,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatEscalationUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
