package com.backintro.application.escalationstatus.command;

import java.util.Objects;

import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateEscalationStatusCommand(
        EscalationStatusId id,
        String nameStatus
) {

    public UpdateEscalationStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
