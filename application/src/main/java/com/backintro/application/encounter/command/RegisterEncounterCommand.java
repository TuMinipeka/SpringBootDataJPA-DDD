package com.backintro.application.encounter.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record RegisterEncounterCommand(
        ClinicalRecordId clinicalRecordId,
        ProfessionalId professionalId,
        EncounterTypeId encounterTypeId,
        LocalDateTime startedAt,
        String reasonForVisit,
        String currentCondition,
        EncounterModalityId modalityId,
        EncounterStatusId statusId
) {

    public RegisterEncounterCommand {
        Objects.requireNonNull(
                clinicalRecordId,
                "clinicalRecordId must not be null"
        );
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        Objects.requireNonNull(
                encounterTypeId,
                "encounterTypeId must not be null"
        );
        Objects.requireNonNull(startedAt, "startedAt must not be null");
        Objects.requireNonNull(modalityId, "modalityId must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
