package com.backintro.application.clinicalrecord.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record UpdateClinicalRecordCommand(
        ClinicalRecordId id,
        String recordNumber,
        LocalDateTime closedAt,
        ClinicalRecordStatusId statusId
) {

    public UpdateClinicalRecordCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
