package com.backintro.infrastructure.encounterstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.encounterstatus.usecase.DeleteEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.GetEncounterStatusByIdUseCase;
import com.backintro.application.encounterstatus.usecase.ListEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.RegisterEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.UpdateEncounterStatusUseCase;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

@Configuration
public class EncounterStatusBeanConfiguration {

    @Bean
    RegisterEncounterStatusUseCase registerEncounterStatusUseCase(
            EncounterStatusRepository repository
    ) {
        return new RegisterEncounterStatusUseCase(repository);
    }

    @Bean
    GetEncounterStatusByIdUseCase getEncounterStatusByIdUseCase(
            EncounterStatusRepository repository
    ) {
        return new GetEncounterStatusByIdUseCase(repository);
    }

    @Bean
    ListEncounterStatusUseCase listEncounterStatusUseCase(
            EncounterStatusRepository repository
    ) {
        return new ListEncounterStatusUseCase(repository);
    }

    @Bean
    UpdateEncounterStatusUseCase updateEncounterStatusUseCase(
            EncounterStatusRepository repository
    ) {
        return new UpdateEncounterStatusUseCase(repository);
    }

    @Bean
    DeleteEncounterStatusUseCase deleteEncounterStatusUseCase(
            EncounterStatusRepository repository
    ) {
        return new DeleteEncounterStatusUseCase(repository);
    }
}
