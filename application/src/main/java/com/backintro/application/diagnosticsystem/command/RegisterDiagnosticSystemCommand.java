package com.backintro.application.diagnosticsystem.command;

import java.util.Objects;

public record RegisterDiagnosticSystemCommand(
        String name,
        String code,
        String version
) {

    public RegisterDiagnosticSystemCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
