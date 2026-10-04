package com.backintro.domain.diagnosticsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.common.event.DomainEvent;

public record DiagnosticSystemRegisteredEvent(
        DiagnosticSystemId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public DiagnosticSystemRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
