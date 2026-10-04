package com.backintro.domain.common.exception;

import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public class DiagnosticSystemNotFoundException extends RuntimeException {

    public DiagnosticSystemNotFoundException(DiagnosticSystemId id) {
        super("Diagnostic system not found with id: " + id.value());
    }
}
