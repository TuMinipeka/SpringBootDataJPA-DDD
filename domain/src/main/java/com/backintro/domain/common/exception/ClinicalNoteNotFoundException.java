package com.backintro.domain.common.exception;

import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public class ClinicalNoteNotFoundException extends RuntimeException {

    public ClinicalNoteNotFoundException(ClinicalNoteId id) {
        super("Clinical note not found with id: " + id.value());
    }
}
