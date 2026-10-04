package com.backintro.application.clinicalrecord.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ClinicalRecordNotFoundApplicationException
        extends ApplicationException {

    public ClinicalRecordNotFoundApplicationException(String message) {
        super(message);
    }
}
