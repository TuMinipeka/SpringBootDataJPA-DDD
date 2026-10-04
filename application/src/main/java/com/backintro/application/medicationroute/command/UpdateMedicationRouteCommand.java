package com.backintro.application.medicationroute.command;

import java.util.Objects;

import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;

public record UpdateMedicationRouteCommand(
        MedicationRouteId id,
        String name,
        String code
) {

    public UpdateMedicationRouteCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
