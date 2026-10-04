package com.backintro.application.treatmentplan.command;

import java.time.LocalDate;
import java.util.Objects;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record RegisterTreatmentPlanCommand(
        EncounterId encounterId,
        ProfessionalId professionalId,
        String title,
        String description,
        LocalDate startDate,
        TreatmentStatusId statusId
) {

    public RegisterTreatmentPlanCommand {
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
