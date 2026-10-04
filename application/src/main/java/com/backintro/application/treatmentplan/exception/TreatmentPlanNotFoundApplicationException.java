package com.backintro.application.treatmentplan.exception;

import com.backintro.application.common.exception.ApplicationException;

public class TreatmentPlanNotFoundApplicationException
        extends ApplicationException {

    public TreatmentPlanNotFoundApplicationException(String message) {
        super(message);
    }
}
