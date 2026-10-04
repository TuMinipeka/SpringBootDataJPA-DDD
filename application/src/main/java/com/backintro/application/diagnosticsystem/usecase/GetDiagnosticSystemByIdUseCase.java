package com.backintro.application.diagnosticsystem.usecase;

import com.backintro.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.backintro.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class GetDiagnosticSystemByIdUseCase {

    private final DiagnosticSystemRepository diagnosticSystemRepository;

    public GetDiagnosticSystemByIdUseCase(
            DiagnosticSystemRepository diagnosticSystemRepository
    ) {
        this.diagnosticSystemRepository = diagnosticSystemRepository;
    }

    public DiagnosticSystemResponse execute(DiagnosticSystemId id) {
        var diagnosticSystem = diagnosticSystemRepository.findById(id)
                .orElseThrow(() ->
                        new DiagnosticSystemNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new DiagnosticSystemResponse(
                diagnosticSystem.id().value(),
                diagnosticSystem.name(),
                diagnosticSystem.code(),
                diagnosticSystem.version()
        );
    }
}
