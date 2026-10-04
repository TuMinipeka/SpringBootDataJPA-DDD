package com.backintro.infrastructure.priority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.priority.usecase.DeletePriorityUseCase;
import com.backintro.application.priority.usecase.GetPriorityByIdUseCase;
import com.backintro.application.priority.usecase.ListPriorityUseCase;
import com.backintro.application.priority.usecase.RegisterPriorityUseCase;
import com.backintro.application.priority.usecase.UpdatePriorityUseCase;
import com.backintro.domain.priority.port.repository.PriorityRepository;

@Configuration
public class PriorityBeanConfiguration {

    @Bean
    RegisterPriorityUseCase registerPriorityUseCase(
            PriorityRepository repository
    ) {
        return new RegisterPriorityUseCase(repository);
    }

    @Bean
    GetPriorityByIdUseCase getPriorityByIdUseCase(
            PriorityRepository repository
    ) {
        return new GetPriorityByIdUseCase(repository);
    }

    @Bean
    ListPriorityUseCase listPriorityUseCase(
            PriorityRepository repository
    ) {
        return new ListPriorityUseCase(repository);
    }

    @Bean
    UpdatePriorityUseCase updatePriorityUseCase(
            PriorityRepository repository
    ) {
        return new UpdatePriorityUseCase(repository);
    }

    @Bean
    DeletePriorityUseCase deletePriorityUseCase(
            PriorityRepository repository
    ) {
        return new DeletePriorityUseCase(repository);
    }
}
