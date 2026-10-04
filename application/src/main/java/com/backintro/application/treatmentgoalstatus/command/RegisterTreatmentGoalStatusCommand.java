package com.backintro.application.treatmentgoalstatus.command;

import java.util.Objects;

public record RegisterTreatmentGoalStatusCommand(
        String name,
        String code
) {

    public RegisterTreatmentGoalStatusCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
