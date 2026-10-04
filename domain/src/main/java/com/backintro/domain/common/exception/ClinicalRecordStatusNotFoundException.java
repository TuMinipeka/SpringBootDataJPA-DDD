package com.backintro.domain.common.exception;

import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public class ClinicalRecordStatusNotFoundException extends RuntimeException {

    public ClinicalRecordStatusNotFoundException(ClinicalRecordStatusId id) {
        super("Clinical record status not found with id: " + id.value());
    }
}
