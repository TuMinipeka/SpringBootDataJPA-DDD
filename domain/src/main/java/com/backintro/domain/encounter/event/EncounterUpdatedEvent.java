package com.backintro.domain.encounter.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record EncounterUpdatedEvent(
        EncounterId id,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        EncounterStatusId statusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
