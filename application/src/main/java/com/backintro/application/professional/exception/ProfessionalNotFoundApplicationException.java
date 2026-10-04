package com.backintro.application.professional.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ProfessionalNotFoundApplicationException
        extends ApplicationException {

    public ProfessionalNotFoundApplicationException(String message) {
        super(message);
    }
}
