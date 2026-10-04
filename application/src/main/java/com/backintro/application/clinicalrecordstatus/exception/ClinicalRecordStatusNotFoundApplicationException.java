package com.backintro.application.clinicalrecordstatus.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ClinicalRecordStatusNotFoundApplicationException
        extends ApplicationException {

    public ClinicalRecordStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
