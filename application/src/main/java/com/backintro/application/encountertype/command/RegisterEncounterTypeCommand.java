package com.backintro.application.encountertype.command;

import java.util.Objects;

public record RegisterEncounterTypeCommand(
        String name,
        String code
) {

    public RegisterEncounterTypeCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
