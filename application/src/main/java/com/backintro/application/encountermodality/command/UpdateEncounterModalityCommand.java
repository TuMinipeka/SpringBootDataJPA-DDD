package com.backintro.application.encountermodality.command;

import java.util.Objects;

import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;

public record UpdateEncounterModalityCommand(
        EncounterModalityId id,
        String name,
        String code
) {

    public UpdateEncounterModalityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
