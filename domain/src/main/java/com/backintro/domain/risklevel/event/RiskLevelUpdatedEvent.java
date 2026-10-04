package com.backintro.domain.risklevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record RiskLevelUpdatedEvent(
        RiskLevelId id,
        String name,
        String code,
        int severity,
        LocalDateTime occurredOn
) implements DomainEvent {

    public RiskLevelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
