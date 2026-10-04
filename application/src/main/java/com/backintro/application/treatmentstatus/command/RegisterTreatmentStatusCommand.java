package com.backintro.application.treatmentstatus.command;

import java.util.Objects;

public record RegisterTreatmentStatusCommand(
        String name,
        String code
) {

    public RegisterTreatmentStatusCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
