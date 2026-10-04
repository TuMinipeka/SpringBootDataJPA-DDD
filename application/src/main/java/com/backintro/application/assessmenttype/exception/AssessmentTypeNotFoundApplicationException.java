package com.backintro.application.assessmenttype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class AssessmentTypeNotFoundApplicationException
        extends ApplicationException {

    public AssessmentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
