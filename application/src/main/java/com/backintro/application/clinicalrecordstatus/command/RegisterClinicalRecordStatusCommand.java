package com.backintro.application.clinicalrecordstatus.command;

import java.util.Objects;

public record RegisterClinicalRecordStatusCommand(
        String name,
        String code
) {

    public RegisterClinicalRecordStatusCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
