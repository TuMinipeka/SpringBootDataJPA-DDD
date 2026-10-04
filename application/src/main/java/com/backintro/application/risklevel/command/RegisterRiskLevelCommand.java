package com.backintro.application.risklevel.command;

import java.util.Objects;

public record RegisterRiskLevelCommand(
        String name,
        String code,
        int severity
) {

    public RegisterRiskLevelCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
