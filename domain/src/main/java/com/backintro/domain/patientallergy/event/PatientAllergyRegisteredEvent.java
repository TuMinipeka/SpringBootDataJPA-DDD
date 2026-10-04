package com.backintro.domain.patientallergy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;

public record PatientAllergyRegisteredEvent(
        PatientAllergyId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PatientAllergyRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
