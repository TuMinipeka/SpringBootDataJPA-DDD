package com.backintro.infrastructure.encountermodality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.encountermodality.usecase.DeleteEncounterModalityUseCase;
import com.backintro.application.encountermodality.usecase.GetEncounterModalityByIdUseCase;
import com.backintro.application.encountermodality.usecase.ListEncounterModalityUseCase;
import com.backintro.application.encountermodality.usecase.RegisterEncounterModalityUseCase;
import com.backintro.application.encountermodality.usecase.UpdateEncounterModalityUseCase;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

@Configuration
public class EncounterModalityBeanConfiguration {

    @Bean
    RegisterEncounterModalityUseCase registerEncounterModalityUseCase(
            EncounterModalityRepository repository
    ) {
        return new RegisterEncounterModalityUseCase(repository);
    }

    @Bean
    GetEncounterModalityByIdUseCase getEncounterModalityByIdUseCase(
            EncounterModalityRepository repository
    ) {
        return new GetEncounterModalityByIdUseCase(repository);
    }

    @Bean
    ListEncounterModalityUseCase listEncounterModalityUseCase(
            EncounterModalityRepository repository
    ) {
        return new ListEncounterModalityUseCase(repository);
    }

    @Bean
    UpdateEncounterModalityUseCase updateEncounterModalityUseCase(
            EncounterModalityRepository repository
    ) {
        return new UpdateEncounterModalityUseCase(repository);
    }

    @Bean
    DeleteEncounterModalityUseCase deleteEncounterModalityUseCase(
            EncounterModalityRepository repository
    ) {
        return new DeleteEncounterModalityUseCase(repository);
    }
}
