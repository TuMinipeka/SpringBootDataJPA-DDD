package com.backintro.application.treatmentgoal.exception;

import com.backintro.application.common.exception.ApplicationException;

public class TreatmentGoalNotFoundApplicationException
        extends ApplicationException {

    public TreatmentGoalNotFoundApplicationException(String message) {
        super(message);
    }
}
