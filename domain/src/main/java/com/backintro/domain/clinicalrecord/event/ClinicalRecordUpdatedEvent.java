package com.backintro.domain.clinicalrecord.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.common.event.DomainEvent;

public record ClinicalRecordUpdatedEvent(
        ClinicalRecordId id,
        String recordNumber,
        LocalDateTime closedAt,
        ClinicalRecordStatusId statusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ClinicalRecordUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
