package com.backintro.infrastructure.airunstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.airunstatus.usecase.DeleteAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.GetAiRunStatusByIdUseCase;
import com.backintro.application.airunstatus.usecase.ListAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.RegisterAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.UpdateAiRunStatusUseCase;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

@Configuration
public class AiRunStatusBeanConfiguration {

    @Bean
    RegisterAiRunStatusUseCase registerAiRunStatusUseCase(
            AiRunStatusRepository repository
    ) {
        return new RegisterAiRunStatusUseCase(repository);
    }

    @Bean
    GetAiRunStatusByIdUseCase getAiRunStatusByIdUseCase(
            AiRunStatusRepository repository
    ) {
        return new GetAiRunStatusByIdUseCase(repository);
    }

    @Bean
    ListAiRunStatusUseCase listAiRunStatusUseCase(
            AiRunStatusRepository repository
    ) {
        return new ListAiRunStatusUseCase(repository);
    }

    @Bean
    UpdateAiRunStatusUseCase updateAiRunStatusUseCase(
            AiRunStatusRepository repository
    ) {
        return new UpdateAiRunStatusUseCase(repository);
    }

    @Bean
    DeleteAiRunStatusUseCase deleteAiRunStatusUseCase(
            AiRunStatusRepository repository
    ) {
        return new DeleteAiRunStatusUseCase(repository);
    }
}
