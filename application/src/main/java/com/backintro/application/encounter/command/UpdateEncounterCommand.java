package com.backintro.application.encounter.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record UpdateEncounterCommand(
        EncounterId id,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        EncounterStatusId statusId
) {

    public UpdateEncounterCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
