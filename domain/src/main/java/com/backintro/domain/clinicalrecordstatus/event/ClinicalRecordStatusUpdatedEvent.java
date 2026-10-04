package com.backintro.domain.clinicalrecordstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.common.event.DomainEvent;

public record ClinicalRecordStatusUpdatedEvent(
        ClinicalRecordStatusId id,
        String name,
        String code,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ClinicalRecordStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
