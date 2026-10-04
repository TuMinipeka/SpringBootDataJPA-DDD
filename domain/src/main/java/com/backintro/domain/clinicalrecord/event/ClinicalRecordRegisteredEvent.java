package com.backintro.domain.clinicalrecord.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.common.event.DomainEvent;

public record ClinicalRecordRegisteredEvent(
        ClinicalRecordId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ClinicalRecordRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
