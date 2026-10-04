package com.backintro.domain.risklevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record RiskLevelRegisteredEvent(
        RiskLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public RiskLevelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
