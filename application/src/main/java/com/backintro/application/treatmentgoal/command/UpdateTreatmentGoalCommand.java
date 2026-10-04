package com.backintro.application.treatmentgoal.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record UpdateTreatmentGoalCommand(
        TreatmentGoalId id,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        TreatmentGoalStatusId statusId
) {

    public UpdateTreatmentGoalCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
