package com.backintro.domain.professionalstudy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record ProfessionalStudyUpdatedEvent(
        ProfessionalStudyId id,
        String title,
        String university,
        boolean valid,
        String resolutionNumber,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ProfessionalStudyUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
