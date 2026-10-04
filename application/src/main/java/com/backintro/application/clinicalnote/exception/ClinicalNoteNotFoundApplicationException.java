package com.backintro.application.clinicalnote.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ClinicalNoteNotFoundApplicationException
        extends ApplicationException {

    public ClinicalNoteNotFoundApplicationException(String message) {
        super(message);
    }
}
