package com.backintro.application.diagnosticsystem.usecase;

import com.backintro.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import com.backintro.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.backintro.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class RegisterDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository diagnosticSystemRepository;

    public RegisterDiagnosticSystemUseCase(
            DiagnosticSystemRepository diagnosticSystemRepository
    ) {
        this.diagnosticSystemRepository = diagnosticSystemRepository;
    }

    public DiagnosticSystemResponse execute(
            RegisterDiagnosticSystemCommand command
    ) {
        DiagnosticSystem diagnosticSystem = DiagnosticSystem.register(
                command.name(),
                command.code(),
                command.version()
        );

        DiagnosticSystem saved =
                diagnosticSystemRepository.save(diagnosticSystem);

        return new DiagnosticSystemResponse(
                saved.id().value(),
                saved.name(),
                saved.code(),
                saved.version()
        );
    }
}
