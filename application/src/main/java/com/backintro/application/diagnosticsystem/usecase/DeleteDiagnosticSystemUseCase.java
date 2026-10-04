package com.backintro.application.diagnosticsystem.usecase;

import java.time.LocalDateTime;

import com.backintro.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.backintro.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class DeleteDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository diagnosticSystemRepository;

    public DeleteDiagnosticSystemUseCase(
            DiagnosticSystemRepository diagnosticSystemRepository
    ) {
        this.diagnosticSystemRepository = diagnosticSystemRepository;
    }

    public DiagnosticSystemDeletedEvent execute(DiagnosticSystemId id) {
        var diagnosticSystem = diagnosticSystemRepository.findById(id)
                .orElseThrow(() ->
                        new DiagnosticSystemNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        diagnosticSystemRepository.delete(diagnosticSystem);

        return new DiagnosticSystemDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
