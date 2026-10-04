package com.backintro.infrastructure.conversationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.conversationstatus.usecase.DeleteConversationStatusUseCase;
import com.backintro.application.conversationstatus.usecase.GetConversationStatusByIdUseCase;
import com.backintro.application.conversationstatus.usecase.ListConversationStatusUseCase;
import com.backintro.application.conversationstatus.usecase.RegisterConversationStatusUseCase;
import com.backintro.application.conversationstatus.usecase.UpdateConversationStatusUseCase;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

@Configuration
public class ConversationStatusBeanConfiguration {

    @Bean
    RegisterConversationStatusUseCase registerConversationStatusUseCase(
            ConversationStatusRepository repository
    ) {
        return new RegisterConversationStatusUseCase(repository);
    }

    @Bean
    GetConversationStatusByIdUseCase getConversationStatusByIdUseCase(
            ConversationStatusRepository repository
    ) {
        return new GetConversationStatusByIdUseCase(repository);
    }

    @Bean
    ListConversationStatusUseCase listConversationStatusUseCase(
            ConversationStatusRepository repository
    ) {
        return new ListConversationStatusUseCase(repository);
    }

    @Bean
    UpdateConversationStatusUseCase updateConversationStatusUseCase(
            ConversationStatusRepository repository
    ) {
        return new UpdateConversationStatusUseCase(repository);
    }

    @Bean
    DeleteConversationStatusUseCase deleteConversationStatusUseCase(
            ConversationStatusRepository repository
    ) {
        return new DeleteConversationStatusUseCase(repository);
    }
}
