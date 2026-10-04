package com.backintro.application.clinicalrecordstatus.command;

import java.util.Objects;

import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record UpdateClinicalRecordStatusCommand(
        ClinicalRecordStatusId id,
        String name,
        String code
) {

    public UpdateClinicalRecordStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}
