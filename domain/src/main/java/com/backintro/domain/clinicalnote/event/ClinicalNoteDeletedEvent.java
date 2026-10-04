package com.backintro.domain.clinicalnote.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.common.event.DomainEvent;

public record ClinicalNoteDeletedEvent(
        ClinicalNoteId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ClinicalNoteDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
