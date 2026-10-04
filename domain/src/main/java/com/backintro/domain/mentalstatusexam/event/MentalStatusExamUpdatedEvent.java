package com.backintro.domain.mentalstatusexam.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public record MentalStatusExamUpdatedEvent(
        MentalStatusExamId id,
        String appearance,
        String behavior,
        String attitude,
        String consciousness,
        String orientation,
        String attention,
        String memory,
        String speech,
        String mood,
        String affect,
        String thoughtProcess,
        String thoughtContent,
        String perception,
        String judgment,
        String insight,
        String psychomotorActivity,
        String observations,
        LocalDateTime occurredOn
) implements DomainEvent {

    public MentalStatusExamUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
