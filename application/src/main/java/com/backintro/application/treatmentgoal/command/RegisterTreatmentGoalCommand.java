package com.backintro.application.treatmentgoal.command;

import java.time.LocalDate;
import java.util.Objects;

import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public record RegisterTreatmentGoalCommand(
        TreatmentPlanId treatmentPlanId,
        String description,
        LocalDate targetDate,
        String notes,
        TreatmentGoalStatusId statusId
) {

    public RegisterTreatmentGoalCommand {
        Objects.requireNonNull(
                treatmentPlanId,
                "treatmentPlanId must not be null"
        );
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
