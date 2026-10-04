package com.backintro.application.encountermodality.command;

import java.util.Objects;

public record RegisterEncounterModalityCommand(
        String name,
        String code
) {

    public RegisterEncounterModalityCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
