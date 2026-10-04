package com.backintro.application.diagnosticsystem.exception;

import com.backintro.application.common.exception.ApplicationException;

public class DiagnosticSystemNotFoundApplicationException
        extends ApplicationException {

    public DiagnosticSystemNotFoundApplicationException(String message) {
        super(message);
    }
}
