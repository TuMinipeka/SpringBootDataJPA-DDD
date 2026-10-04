package com.backintro.application.encounterstatus.command;

import java.util.Objects;

public record RegisterEncounterStatusCommand(
        String name,
        String code
) {

    public RegisterEncounterStatusCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
