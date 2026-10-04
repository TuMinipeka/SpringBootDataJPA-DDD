package com.backintro.domain.patientcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;

public record PatientContactUpdatedEvent(
        PatientContactId id,
        boolean primaryContact,
        boolean emergencyContact,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PatientContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
