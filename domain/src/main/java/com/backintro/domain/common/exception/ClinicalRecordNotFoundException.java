package com.backintro.domain.common.exception;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public class ClinicalRecordNotFoundException extends RuntimeException {

    public ClinicalRecordNotFoundException(ClinicalRecordId id) {
        super("Clinical record not found with id: " + id.value());
    }
}
