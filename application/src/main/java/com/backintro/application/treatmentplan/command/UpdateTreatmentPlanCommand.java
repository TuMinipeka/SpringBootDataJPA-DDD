package com.backintro.application.treatmentplan.command;

import java.time.LocalDate;
import java.util.Objects;

import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record UpdateTreatmentPlanCommand(
        TreatmentPlanId id,
        String title,
        String description,
        LocalDate endDate,
        TreatmentStatusId statusId
) {

    public UpdateTreatmentPlanCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
