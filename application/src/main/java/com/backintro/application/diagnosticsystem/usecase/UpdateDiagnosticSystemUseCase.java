package com.backintro.application.diagnosticsystem.usecase;

import com.backintro.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import com.backintro.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.backintro.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class UpdateDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository diagnosticSystemRepository;

    public UpdateDiagnosticSystemUseCase(
            DiagnosticSystemRepository diagnosticSystemRepository
    ) {
        this.diagnosticSystemRepository = diagnosticSystemRepository;
    }

    public DiagnosticSystemResponse execute(
            UpdateDiagnosticSystemCommand command
    ) {
        var diagnosticSystem = diagnosticSystemRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new DiagnosticSystemNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        diagnosticSystem.update(
                command.name(),
                command.code(),
                command.version()
        );

        var updated = diagnosticSystemRepository.save(diagnosticSystem);

        return new DiagnosticSystemResponse(
                updated.id().value(),
                updated.name(),
                updated.code(),
                updated.version()
        );
    }
}
