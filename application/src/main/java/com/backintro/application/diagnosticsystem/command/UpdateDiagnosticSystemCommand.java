package com.backintro.application.diagnosticsystem.command;

import java.util.Objects;

import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public record UpdateDiagnosticSystemCommand(
        DiagnosticSystemId id,
        String name,
        String code,
        String version
) {

    public UpdateDiagnosticSystemCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
