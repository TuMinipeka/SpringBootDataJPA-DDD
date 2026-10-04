package com.backintro.domain.diagnosticsystem.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;
import com.backintro.domain.diagnosticsystem.event.DiagnosticSystemUpdatedEvent;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.common.model.AggregateRoot;

public class DiagnosticSystem extends AggregateRoot {

    private final DiagnosticSystemId id;
    private String name;
    private String code;
    private boolean active;
    private String version;

    private DiagnosticSystem(
            DiagnosticSystemId id,
            String name,
            String code,
            boolean active,
            String version
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
        this.version = version;
    }

    public static DiagnosticSystem register(
            String name,
            String code,
            String version
    ) {
        DiagnosticSystemId id = DiagnosticSystemId.generate();
        DiagnosticSystem diagnosticSystem = new DiagnosticSystem(
                id,
                name,
                code,
                true,
                version
        );

        diagnosticSystem.recordEvent(
                new DiagnosticSystemRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return diagnosticSystem;
    }

    public static DiagnosticSystem restore(
            DiagnosticSystemId id,
            String name,
            String code,
            boolean active,
            String version
    ) {
        return new DiagnosticSystem(id, name, code, active, version);
    }

    public void update(
            String name,
            String code,
            String version
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.version = version;

        recordEvent(
                new DiagnosticSystemUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        this.version,
                        LocalDateTime.now()
                )
        );
    }

    public DiagnosticSystemId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public boolean active() {
        return active;
    }

    public String version() {
        return version;
    }
}
