package com.backintro.application.encountertype.command;

import java.util.Objects;

import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public record UpdateEncounterTypeCommand(
        EncounterTypeId id,
        String name,
        String code
) {

    public UpdateEncounterTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
