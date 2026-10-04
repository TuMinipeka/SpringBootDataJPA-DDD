package com.backintro.application.risklevel.command;

import java.util.Objects;

import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record UpdateRiskLevelCommand(
        RiskLevelId id,
        String name,
        String code,
        int severity
) {

    public UpdateRiskLevelCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
