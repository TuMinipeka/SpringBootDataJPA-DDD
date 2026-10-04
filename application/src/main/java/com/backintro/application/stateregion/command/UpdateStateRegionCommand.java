package com.backintro.application.stateregion.command;

import java.util.Objects;

import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public record UpdateStateRegionCommand(
        StateRegionId id,
        String nameRegion,
        String codeRegion
) {

    public UpdateStateRegionCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        Objects.requireNonNull(codeRegion, "codeRegion must not be null");
    }
}
