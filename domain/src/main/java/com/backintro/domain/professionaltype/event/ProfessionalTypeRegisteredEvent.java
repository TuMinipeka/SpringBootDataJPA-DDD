package com.backintro.domain.professionaltype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record ProfessionalTypeRegisteredEvent(
        ProfessionalTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ProfessionalTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
