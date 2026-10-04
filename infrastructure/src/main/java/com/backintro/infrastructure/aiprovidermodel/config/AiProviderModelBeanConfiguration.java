package com.backintro.infrastructure.aiprovidermodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.aiprovidermodel.usecase.DeleteAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.GetAiProviderModelByIdUseCase;
import com.backintro.application.aiprovidermodel.usecase.ListAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.RegisterAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.UpdateAiProviderModelUseCase;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

@Configuration
public class AiProviderModelBeanConfiguration {

    @Bean
    RegisterAiProviderModelUseCase registerAiProviderModelUseCase(
            AiProviderModelRepository repository
    ) {
        return new RegisterAiProviderModelUseCase(repository);
    }

    @Bean
    GetAiProviderModelByIdUseCase getAiProviderModelByIdUseCase(
            AiProviderModelRepository repository
    ) {
        return new GetAiProviderModelByIdUseCase(repository);
    }

    @Bean
    ListAiProviderModelUseCase listAiProviderModelUseCase(
            AiProviderModelRepository repository
    ) {
        return new ListAiProviderModelUseCase(repository);
    }

    @Bean
    UpdateAiProviderModelUseCase updateAiProviderModelUseCase(
            AiProviderModelRepository repository
    ) {
        return new UpdateAiProviderModelUseCase(repository);
    }

    @Bean
    DeleteAiProviderModelUseCase deleteAiProviderModelUseCase(
            AiProviderModelRepository repository
    ) {
        return new DeleteAiProviderModelUseCase(repository);
    }
}
