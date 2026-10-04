package com.backintro.application.clinicalrecord.command;

import java.util.Objects;

import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.patient.model.valueobject.PatientId;

public record RegisterClinicalRecordCommand(
        PatientId patientId,
        String recordNumber,
        ClinicalRecordStatusId statusId
) {

    public RegisterClinicalRecordCommand {
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
    }
}
