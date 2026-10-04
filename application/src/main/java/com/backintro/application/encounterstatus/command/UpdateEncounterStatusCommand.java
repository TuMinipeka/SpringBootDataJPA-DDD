package com.backintro.application.encounterstatus.command;

import java.util.Objects;

import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record UpdateEncounterStatusCommand(
        EncounterStatusId id,
        String name,
        String code
) {

    public UpdateEncounterStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
