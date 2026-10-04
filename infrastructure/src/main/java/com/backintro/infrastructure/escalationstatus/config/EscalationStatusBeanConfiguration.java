package com.backintro.infrastructure.escalationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.escalationstatus.usecase.DeleteEscalationStatusUseCase;
import com.backintro.application.escalationstatus.usecase.GetEscalationStatusByIdUseCase;
import com.backintro.application.escalationstatus.usecase.ListEscalationStatusUseCase;
import com.backintro.application.escalationstatus.usecase.RegisterEscalationStatusUseCase;
import com.backintro.application.escalationstatus.usecase.UpdateEscalationStatusUseCase;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

@Configuration
public class EscalationStatusBeanConfiguration {

    @Bean
    RegisterEscalationStatusUseCase registerEscalationStatusUseCase(
            EscalationStatusRepository repository
    ) {
        return new RegisterEscalationStatusUseCase(repository);
    }

    @Bean
    GetEscalationStatusByIdUseCase getEscalationStatusByIdUseCase(
            EscalationStatusRepository repository
    ) {
        return new GetEscalationStatusByIdUseCase(repository);
    }

    @Bean
    ListEscalationStatusUseCase listEscalationStatusUseCase(
            EscalationStatusRepository repository
    ) {
        return new ListEscalationStatusUseCase(repository);
    }

    @Bean
    UpdateEscalationStatusUseCase updateEscalationStatusUseCase(
            EscalationStatusRepository repository
    ) {
        return new UpdateEscalationStatusUseCase(repository);
    }

    @Bean
    DeleteEscalationStatusUseCase deleteEscalationStatusUseCase(
            EscalationStatusRepository repository
    ) {
        return new DeleteEscalationStatusUseCase(repository);
    }
}
