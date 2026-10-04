package com.backintro.application.risklevel.exception;

import com.backintro.application.common.exception.ApplicationException;

public class RiskLevelNotFoundApplicationException
        extends ApplicationException {

    public RiskLevelNotFoundApplicationException(String message) {
        super(message);
    }
}
