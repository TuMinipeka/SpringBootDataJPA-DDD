package com.backintro.infrastructure.encountertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import com.backintro.application.encountertype.usecase.ListEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

@Configuration
public class EncounterTypeBeanConfiguration {

    @Bean
    RegisterEncounterTypeUseCase registerEncounterTypeUseCase(
            EncounterTypeRepository repository
    ) {
        return new RegisterEncounterTypeUseCase(repository);
    }

    @Bean
    GetEncounterTypeByIdUseCase getEncounterTypeByIdUseCase(
            EncounterTypeRepository repository
    ) {
        return new GetEncounterTypeByIdUseCase(repository);
    }

    @Bean
    ListEncounterTypeUseCase listEncounterTypeUseCase(
            EncounterTypeRepository repository
    ) {
        return new ListEncounterTypeUseCase(repository);
    }

    @Bean
    UpdateEncounterTypeUseCase updateEncounterTypeUseCase(
            EncounterTypeRepository repository
    ) {
        return new UpdateEncounterTypeUseCase(repository);
    }

    @Bean
    DeleteEncounterTypeUseCase deleteEncounterTypeUseCase(
            EncounterTypeRepository repository
    ) {
        return new DeleteEncounterTypeUseCase(repository);
    }
}
