package com.backintro.infrastructure.aimodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.aimodel.usecase.DeleteAiModelUseCase;
import com.backintro.application.aimodel.usecase.GetAiModelByIdUseCase;
import com.backintro.application.aimodel.usecase.ListAiModelUseCase;
import com.backintro.application.aimodel.usecase.RegisterAiModelUseCase;
import com.backintro.application.aimodel.usecase.UpdateAiModelUseCase;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

@Configuration
public class AiModelBeanConfiguration {

    @Bean
    RegisterAiModelUseCase registerAiModelUseCase(
            AiModelRepository repository
    ) {
        return new RegisterAiModelUseCase(repository);
    }

    @Bean
    GetAiModelByIdUseCase getAiModelByIdUseCase(
            AiModelRepository repository
    ) {
        return new GetAiModelByIdUseCase(repository);
    }

    @Bean
    ListAiModelUseCase listAiModelUseCase(
            AiModelRepository repository
    ) {
        return new ListAiModelUseCase(repository);
    }

    @Bean
    UpdateAiModelUseCase updateAiModelUseCase(
            AiModelRepository repository
    ) {
        return new UpdateAiModelUseCase(repository);
    }

    @Bean
    DeleteAiModelUseCase deleteAiModelUseCase(
            AiModelRepository repository
    ) {
        return new DeleteAiModelUseCase(repository);
    }
}
