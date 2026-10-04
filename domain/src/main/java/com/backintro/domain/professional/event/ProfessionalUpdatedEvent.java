package com.backintro.domain.professional.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record ProfessionalUpdatedEvent(
        ProfessionalId id,
        String documentNumber,
        String firstName,
        String lastName,
        String licenseNumber,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ProfessionalUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                documentNumber,
                "documentNumber must not be null"
        );
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
