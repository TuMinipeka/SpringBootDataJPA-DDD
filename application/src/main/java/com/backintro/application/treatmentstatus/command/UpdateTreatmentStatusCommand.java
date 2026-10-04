package com.backintro.application.treatmentstatus.command;

import java.util.Objects;

import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record UpdateTreatmentStatusCommand(
        TreatmentStatusId id,
        String name,
        String code
) {

    public UpdateTreatmentStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
