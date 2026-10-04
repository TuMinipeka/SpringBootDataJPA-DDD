package com.backintro.domain.clinicalrecordstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.common.event.DomainEvent;

public record ClinicalRecordStatusRegisteredEvent(
        ClinicalRecordStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ClinicalRecordStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
