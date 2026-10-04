package com.backintro.application.diagnosticsystem.usecase;

import java.util.List;

import com.backintro.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class ListDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository diagnosticSystemRepository;

    public ListDiagnosticSystemUseCase(
            DiagnosticSystemRepository diagnosticSystemRepository
    ) {
        this.diagnosticSystemRepository = diagnosticSystemRepository;
    }

    public List<DiagnosticSystemResponse> execute() {
        return diagnosticSystemRepository.findAll()
                .stream()
                .map(diagnosticSystem ->
                        new DiagnosticSystemResponse(
                                diagnosticSystem.id().value(),
                                diagnosticSystem.name(),
                                diagnosticSystem.code(),
                                diagnosticSystem.version()
                        )
                )
                .toList();
    }
}
