package com.backintro.domain.patient.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.patient.model.valueobject.PatientId;

public record PatientUpdatedEvent(
        PatientId id,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        String email,
        String phone,
        String address,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PatientUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                documentNumber,
                "documentNumber must not be null"
        );
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(birthDate, "birthDate must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
