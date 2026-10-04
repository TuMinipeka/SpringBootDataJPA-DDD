package com.backintro.infrastructure.chatairunerror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatairunerror.usecase.DeleteChatAiRunErrorUseCase;
import com.backintro.application.chatairunerror.usecase.GetChatAiRunErrorByIdUseCase;
import com.backintro.application.chatairunerror.usecase.ListChatAiRunErrorUseCase;
import com.backintro.application.chatairunerror.usecase.RegisterChatAiRunErrorUseCase;
import com.backintro.application.chatairunerror.usecase.UpdateChatAiRunErrorUseCase;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

@Configuration
public class ChatAiRunErrorBeanConfiguration {

    @Bean
    RegisterChatAiRunErrorUseCase registerChatAiRunErrorUseCase(
            ChatAiRunErrorRepository errorRepository,
            ChatAiRunRepository chatAiRunRepository
    ) {
        return new RegisterChatAiRunErrorUseCase(
                errorRepository,
                chatAiRunRepository
        );
    }

    @Bean
    GetChatAiRunErrorByIdUseCase getChatAiRunErrorByIdUseCase(
            ChatAiRunErrorRepository repository
    ) {
        return new GetChatAiRunErrorByIdUseCase(repository);
    }

    @Bean
    ListChatAiRunErrorUseCase listChatAiRunErrorUseCase(
            ChatAiRunErrorRepository repository
    ) {
        return new ListChatAiRunErrorUseCase(repository);
    }

    @Bean
    UpdateChatAiRunErrorUseCase updateChatAiRunErrorUseCase(
            ChatAiRunErrorRepository repository
    ) {
        return new UpdateChatAiRunErrorUseCase(repository);
    }

    @Bean
    DeleteChatAiRunErrorUseCase deleteChatAiRunErrorUseCase(
            ChatAiRunErrorRepository repository
    ) {
        return new DeleteChatAiRunErrorUseCase(repository);
    }
}
