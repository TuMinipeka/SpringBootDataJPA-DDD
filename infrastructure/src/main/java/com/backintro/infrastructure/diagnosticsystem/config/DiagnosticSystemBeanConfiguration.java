package com.backintro.infrastructure.diagnosticsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.diagnosticsystem.usecase.DeleteDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.GetDiagnosticSystemByIdUseCase;
import com.backintro.application.diagnosticsystem.usecase.ListDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.RegisterDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.UpdateDiagnosticSystemUseCase;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

@Configuration
public class DiagnosticSystemBeanConfiguration {

    @Bean
    RegisterDiagnosticSystemUseCase registerDiagnosticSystemUseCase(
            DiagnosticSystemRepository repository
    ) {
        return new RegisterDiagnosticSystemUseCase(repository);
    }

    @Bean
    GetDiagnosticSystemByIdUseCase getDiagnosticSystemByIdUseCase(
            DiagnosticSystemRepository repository
    ) {
        return new GetDiagnosticSystemByIdUseCase(repository);
    }

    @Bean
    ListDiagnosticSystemUseCase listDiagnosticSystemUseCase(
            DiagnosticSystemRepository repository
    ) {
        return new ListDiagnosticSystemUseCase(repository);
    }

    @Bean
    UpdateDiagnosticSystemUseCase updateDiagnosticSystemUseCase(
            DiagnosticSystemRepository repository
    ) {
        return new UpdateDiagnosticSystemUseCase(repository);
    }

    @Bean
    DeleteDiagnosticSystemUseCase deleteDiagnosticSystemUseCase(
            DiagnosticSystemRepository repository
    ) {
        return new DeleteDiagnosticSystemUseCase(repository);
    }
}
