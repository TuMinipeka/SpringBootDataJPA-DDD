package com.backintro.application.medicationroute.command;

import java.util.Objects;

public record RegisterMedicationRouteCommand(
        String name,
        String code
) {

    public RegisterMedicationRouteCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
