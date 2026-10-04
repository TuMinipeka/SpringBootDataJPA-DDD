package com.backintro.domain.diagnosticsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.common.event.DomainEvent;

public record DiagnosticSystemUpdatedEvent(
        DiagnosticSystemId id,
        String name,
        String code,
        String version,
        LocalDateTime occurredOn
) implements DomainEvent {

    public DiagnosticSystemUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
